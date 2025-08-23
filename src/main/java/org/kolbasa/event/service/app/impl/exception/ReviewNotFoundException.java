package org.kolbasa.event.service.app.impl.exception;

import static java.lang.String.format;

public class ReviewNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Review not found by id: %s";

    public ReviewNotFoundException(Long message) {
        super(format(MESSAGE, message));
    }
}