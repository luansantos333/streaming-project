package org.portfolio.streaming.controllers.handlers;


import jakarta.servlet.http.HttpServletRequest;
import org.portfolio.streaming.controllers.handlers.dtos.CustomFieldMessageError;
import org.portfolio.streaming.controllers.handlers.dtos.DefaultErrorDTO;
import org.portfolio.streaming.services.exceptions.DatabaseException;
import org.portfolio.streaming.services.exceptions.ForbiddenException;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

@Configuration
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity<DefaultErrorDTO> resourceNotFoundHandler (ResourceNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        DefaultErrorDTO err = new DefaultErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);

    }

    @ExceptionHandler (DatabaseException.class)
    public ResponseEntity<DefaultErrorDTO> databaseExceptionHandler (DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        DefaultErrorDTO err = new DefaultErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);

    }

    @ExceptionHandler (ForbiddenException.class)
    public ResponseEntity<DefaultErrorDTO> forbiddenAccessHandler (ForbiddenException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.FORBIDDEN;
        DefaultErrorDTO err = new DefaultErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);


    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<CustomFieldMessageError> validationErrorExceptionHandler (MethodArgumentNotValidException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomFieldMessageError err = new CustomFieldMessageError(Instant.now(), status.value(), request.getRequestURI());

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {

            err.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());

        }

        return ResponseEntity.status(status).body(err);

    }




}
