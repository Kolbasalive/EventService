package org.kolbasa.event.service.domain.exception;

public class InvalidPasswordException extends RuntimeException {
    private static final String MESSAGE = "Invalid password";

    public InvalidPasswordException(String password) {
        super(MESSAGE);
    }
}
