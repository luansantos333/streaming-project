package org.portfolio.streaming.controllers.handlers;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@Configuration
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> resourceNotFoundHandler (ResourceNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO err = new ErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);

    }





}
