package com.jjst.rentManagement.renthouse.util;

import java.security.SecureRandom;
import java.util.Base64;


public class Utility {
    public static String extractUsername(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        String username = email.substring(0, email.indexOf("@"));
        return username.toLowerCase();
    }

    public static String generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32];
        secureRandom.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        //System.out.println("Generated Secret Key: " + encodedKey);
        return encodedKey;
    }
}

