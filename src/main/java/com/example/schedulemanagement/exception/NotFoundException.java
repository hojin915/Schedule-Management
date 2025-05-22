package com.example.schedulemanagement.exception;

public class NotFoundException extends RuntimeException {
    private final String logMessage;

    public NotFoundException(String logMessage, String message) {
        super(message);
        this.logMessage = logMessage;
    }

    public String getLogMessage() {
        return logMessage;
    }
}
