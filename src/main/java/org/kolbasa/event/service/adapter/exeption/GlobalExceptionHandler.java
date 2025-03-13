package org.kolbasa.event.service.adapter.exeption;

import lombok.extern.slf4j.Slf4j;
import org.kolbasa.event.service.domain.exception.InvalidEventTimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidEventTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handle(InvalidEventTimeException exception) {
        LOGGER.error("Event error: {}", exception.getMessage());
        return exception.getMessage();
    }
}