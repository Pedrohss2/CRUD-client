package com.crud.client.controllers.hendlers;

import com.crud.client.dto.CustomError;
import com.crud.client.services.exceptions.ResoruceNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(ResoruceNotFound.class)
    public ResponseEntity<CustomError> resorceNotFound(ResoruceNotFound resoruceNotFound, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND; // 414
        CustomError customError = new CustomError(Instant.now(), status.value(), resoruceNotFound.getMessage(), request.getRequestURI());

        return  ResponseEntity.status(status).body(customError);

    }
}
