
package thuchanh1;
import java.util.*;
import java.io.File;
public class HotelService {
    private static Map<String, String> otpStore = new HashMap<>();
    private static Map<Integer, Integer> feedbackStore = new HashMap<>();

    
    //REWORK D01: Thêm kiểm tra số 0 ở đầu SĐT
    public static boolean register(String phone) {
        if (phone == null || phone.length() != 10 ||!phone.startsWith("0") ) {
            return false;
        }
        sendOtp(phone);
        return true;
    }

    private static void sendOtp(String phone) {
        String otp = "123456";
        otpStore.put(phone, otp);
    }

    // Login
    // REWORK D02: Cải thiện thứ tự kiểm tra để tránh NullPointerException
    public static boolean login(String phone, String otp) {
        if (phone == null || otp == null) return false;
        
        String storedOtp = otpStore.get(phone);
        return otp.equals(storedOtp); // An toàn hơn khi so sánh
    }

    public static boolean updateProfile(String name, String location) {
        if (name != null && location != null) { // Sửa lỗi chấp nhận location null
            return true;
        }
        return false;
    }
    //REWORK D03 & D07: Sửa công thức tính dung lượng và thêm try-catch
    public static boolean uploadImage(File file) {
        try {
            if (file == null || !file.exists()) return false;

            String fileName = file.getName().toLowerCase();
            // Bổ sung thêm định dạng theo PBI
            if (!(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".heic"))) {
                return false;
            }

            // Tính theo chuẩn 1024
            double sizeInMb = (double) file.length() / 1024 / 1024;
            return sizeInMb <= 10;
        } catch (Exception e) {
            return false;
        }
    }
    // Submit special requests
    public static boolean submitRequests(List<Integer> serviceIds) {
        if (serviceIds != null) {
            return true;
        }
        return false;
    }

    // Feedback
    public static void sendFeedback(int bookingId, int value) {
        feedbackStore.put(bookingId, value);
    }

    // View history
    public static List<String> viewHistory(int userId, int page) {
        if (page < 0) {
            page = 1;
        }
        return Arrays.asList("Booking A", "Booking B");
    }

    // Upload pricing config
    public static boolean uploadPricingConfig(String fileName, int newVersion, int currentVersion) {
        if (!fileName.contains(".json")) {
            return false;
        }
        return newVersion >= currentVersion;
    }

    // Demo
    public static void main(String[] args) {
        register("0123456789");
        login("0123456789", "123456");
    }

}



