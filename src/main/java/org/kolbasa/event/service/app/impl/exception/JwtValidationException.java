package org.kolbasa.event.service.app.impl.exception;

import static java.lang.String.format;

public class JwtValidationException extends RuntimeException {
    private static final String MESSAGE = "JWT validation failed: %s";
    public JwtValidationException(String errorMessage) {
        super(format(MESSAGE, errorMessage));
    }
}