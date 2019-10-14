package com.example.phasetwo.common;

public class TimeSlotDoesNotExistException extends Exception {
    public TimeSlotDoesNotExistException() {
        super();
    }

    public TimeSlotDoesNotExistException(String message) {
        super(message);
    }

    public TimeSlotDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeSlotDoesNotExistException(Throwable cause) {
        super(cause);
    }

    protected TimeSlotDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
