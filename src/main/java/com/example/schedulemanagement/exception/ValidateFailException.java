package com.example.schedulemanagement.exception;

public class ValidateFailException extends RuntimeException {
    private final String logMessage;

    public ValidateFailException(String logMessage, String message) {
        super(message);
        this.logMessage = logMessage;
    }

    public String getLogMessage() {
        return logMessage;
    }
}
