package org.kolbasa.event.service.adapter.http.exeption;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.kolbasa.event.service.adapter.http.dto.ErrorResponseDto;
import org.kolbasa.event.service.domain.exception.EventNotFoundException;
import org.kolbasa.event.service.domain.exception.InvalidEventTimeException;
import org.kolbasa.event.service.domain.exception.JwtValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String EVENT_EXCEPTION_MESSAGE = "Event Exception Message: {}";

    @ExceptionHandler(InvalidEventTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto handleInvalidEventTimeException(InvalidEventTimeException exception, HttpServletRequest request) {
        LOGGER.error(EVENT_EXCEPTION_MESSAGE, exception.getMessage());
        return createErrorResponseDto(exception, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDto handleEventNotFoundException(EventNotFoundException exception, HttpServletRequest request) {
        LOGGER.error(EVENT_EXCEPTION_MESSAGE, exception.getMessage());
        return createErrorResponseDto(exception, request, HttpStatus.NOT_FOUND);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private ErrorResponseDto createErrorResponseDto(Throwable exception, HttpServletRequest request, HttpStatus httpStatus) {
        return new ErrorResponseDto(
                exception.getMessage(),
                request.getRequestURI(),
                httpStatus.value(),
                LocalDateTime.now()
        );
    }
}