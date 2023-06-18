package com.OnAnOn.utility;



public class Admin {
    private static final String username = "admin";
    private static final String password = "admin123";

    public static boolean authenticate(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
}


