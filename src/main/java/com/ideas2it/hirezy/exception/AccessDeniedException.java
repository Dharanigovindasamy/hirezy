package com.ideas2it.hirezy.exception;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {

    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
