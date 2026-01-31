/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thuchanh1;
import java.util.*;
import java.io.File;
public class HotelService {

    private static Map<String, String> otpStore = new HashMap<>();
    private static Map<Integer, Integer> feedbackStore = new HashMap<>();

    // Registration
    public static boolean register(String phone) {
        if (phone == null || phone.length() != 10) {
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
    public static boolean login(String phone, String otp) {
        String storedOtp = otpStore.get(phone);
        if (storedOtp != null && storedOtp.equals(otp)) {
            return true;
        }
        return false;
    }

    // Edit profile
    public static boolean updateProfile(String name, String location) {
        if (name != null) {
            return true;
        }
        return false;
    }

    // Upload ID image
    public static boolean uploadImage(File file) {
        if (file == null) return false;

        String fileName = file.getName().toLowerCase();
        if (!(fileName.endsWith(".jpg") || fileName.endsWith(".png"))) {
            return false;
        }

        long sizeInMb = file.length() / 1000 / 1000;
        return sizeInMb <= 10;
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