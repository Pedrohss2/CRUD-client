package com.crud.client.controllers.handlers;

import com.crud.client.dto.CustomError;
import com.crud.client.dto.ValidationError;
import com.crud.client.services.exceptions.ResourceNotFoundExceptions;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    public CustomError customError;

    @ExceptionHandler(ResourceNotFoundExceptions.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundExceptions resourceNotFoundExceptions, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND; // 414
        customError = new CustomError(Instant.now(), status.value(), resourceNotFoundExceptions.getMessage(), request.getRequestURI());

        return  ResponseEntity.status(status).body(customError);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> resourceNotFound(MethodArgumentNotValidException error, HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError errors = new ValidationError(Instant.now(), status.value(), error.getMessage(), request.getRequestURI());

        for (FieldError fieldError : error.getBindingResult().getFieldErrors()) {
            errors.addMessage(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(errors);
    }

}
