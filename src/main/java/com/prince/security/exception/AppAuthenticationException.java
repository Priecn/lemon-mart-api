package com.prince.security.exception;

public class AppAuthenticationException extends RuntimeException {

    public AppAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
