package com.vgorbatiouk.userrestapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ErrorMessage> handleUserException(UserException exception, WebRequest request) {
        ErrorMessage errorModel = ErrorMessage.builder().message(exception.getMessage()).status(exception.getStatus())
            .build();
        return new ResponseEntity(errorModel, errorModel.getStatus());

    }

}