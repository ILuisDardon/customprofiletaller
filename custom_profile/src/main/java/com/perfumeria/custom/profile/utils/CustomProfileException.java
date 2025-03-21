package com.perfumeria.custom.profile.utils;

public class CustomProfileException extends Exception{
    
    public CustomProfileException(String message) {
        super(message);
    }

    public CustomProfileException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
