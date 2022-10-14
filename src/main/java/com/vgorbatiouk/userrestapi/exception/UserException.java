package com.vgorbatiouk.userrestapi.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class UserException extends RuntimeException {

    private String message;
    private HttpStatus status;

    public UserException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}