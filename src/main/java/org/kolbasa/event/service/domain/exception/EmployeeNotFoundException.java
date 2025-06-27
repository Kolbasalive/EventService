package org.kolbasa.event.service.domain.exception;

import static java.lang.String.format;

public class EmployeeNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Employee not found, login: {}";

    public EmployeeNotFoundException(String login) {
        super(format(MESSAGE, login));
    }
}
