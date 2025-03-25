package com.jjst.rentManagement.renthouse.util;

public class Utility {
    public static String extractUsername(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        String username = email.substring(0, email.indexOf("@"));
        return username.toLowerCase();
    }
}
