package org.kolbasa.eventhub.app.impl.exception;

import java.time.LocalDateTime;

import static java.lang.String.format;

public class DateNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Некорректная дата: %s";

    public DateNotFoundException(LocalDateTime date) {
        super(format(MESSAGE, date));
    }
}
