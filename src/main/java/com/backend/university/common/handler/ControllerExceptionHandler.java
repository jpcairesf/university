package com.backend.university.common.handler;

import com.backend.university.common.error.BusinessException;
import com.backend.university.common.error.rest.APIErrorResponse;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@CommonsLog
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({
            NoSuchElementException.class,
            EntityNotFoundException.class,
            EmptyResultDataAccessException.class
    })
    public ResponseEntity<APIErrorResponse> handleOptionalNotFoundException(Exception ex, WebRequest request) {
        return this.handleError(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return this.handleError(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<APIErrorResponse> handleBusinessException(BusinessException ex, WebRequest request) {
        return this.handleError(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return this.handleError(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return this.handleError(HttpStatus.BAD_REQUEST, ex, request);
    }

    private ResponseEntity<APIErrorResponse> handleError(HttpStatus status, Exception ex, WebRequest request) {
        log.error(ex);
        return this.getResponse(status, ex, request);
    }

    private ResponseEntity<APIErrorResponse> getResponse(HttpStatus status, Exception ex, WebRequest request) {
        APIErrorResponse message = new APIErrorResponse(
                ex.getMessage(),
                status.value(),
                status.getReasonPhrase(),
                LocalDate.now(),
                request.getDescription(false));
        return ResponseEntity.status(status).body(message);
    }

}
