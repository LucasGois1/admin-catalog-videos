package com.lucasgois.admin.catalog.domain.shared.exceptions;

public class NoStackTraceException extends RuntimeException {
    public NoStackTraceException(String message) {
        this(message, null);
    }

    public NoStackTraceException(String message, Throwable cause) {
        super(message, cause, true, false);
    }
}
