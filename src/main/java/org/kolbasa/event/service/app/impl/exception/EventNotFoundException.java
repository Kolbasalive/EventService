package org.kolbasa.event.service.app.impl.exception;

import static java.lang.String.format;

public class EventNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Event not found by id: %s";

    public EventNotFoundException(Long id) {
        super(format(MESSAGE, id));
    }
}

