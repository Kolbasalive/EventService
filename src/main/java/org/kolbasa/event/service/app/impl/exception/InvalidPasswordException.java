package org.kolbasa.event.service.app.impl.exception;

public class InvalidPasswordException extends RuntimeException {
    private static final String MESSAGE = "Invalid password";

    public InvalidPasswordException(String password) {
        super(MESSAGE);
    }
}
