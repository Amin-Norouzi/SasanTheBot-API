package dev.aminnorouzi.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalUserRequestException extends RuntimeException {

    public IllegalUserRequestException(String message) {
        super(message);
    }
}
