package org.kolbasa.event.service.app.impl.exception;

public class InvalidRefreshTokenException extends RuntimeException {

    public InvalidRefreshTokenException() {
        super("Refresh token is missing, expired, or revoked");
    }
}
