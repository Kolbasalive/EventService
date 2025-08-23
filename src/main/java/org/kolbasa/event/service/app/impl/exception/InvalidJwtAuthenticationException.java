package org.kolbasa.event.service.app.impl.exception;

import static java.lang.String.format;

public class InvalidJwtAuthenticationException extends RuntimeException {
    private static final String MESSAGE = "Invalid JWT token: %s";
    public InvalidJwtAuthenticationException(String message) {
        super(format(MESSAGE, message));
    }
}