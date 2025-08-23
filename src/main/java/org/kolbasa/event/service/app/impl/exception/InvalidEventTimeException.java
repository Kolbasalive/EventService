package org.kolbasa.event.service.app.impl.exception;

import java.time.LocalDateTime;

import static java.lang.String.format;

public class InvalidEventTimeException extends RuntimeException{
    private static final String INVALID_TIME_MESSAGE = "Invalid event time: %s";

    public InvalidEventTimeException(LocalDateTime message) {
        super(format(INVALID_TIME_MESSAGE, message));
    }
}
