package org.kolbasa.event.service.domain.exception;

import static java.lang.String.format;

public class JsonSerializationException extends RuntimeException{
    private static final String MESSAGE = "JSON serialization exception: %s";

    public JsonSerializationException(String message) {
        super(format(MESSAGE, message));
    }
}