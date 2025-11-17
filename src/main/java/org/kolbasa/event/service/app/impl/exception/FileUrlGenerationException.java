package org.kolbasa.event.service.app.impl.exception;

import static java.lang.String.format;

public class FileUrlGenerationException extends RuntimeException {
    private static final String MESSAGE_ERROR = "File not found %s";

    public FileUrlGenerationException(Exception message) {
        super(format(MESSAGE_ERROR, message.getMessage()));
    }
}