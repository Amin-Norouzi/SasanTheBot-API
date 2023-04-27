package dev.aminnorouzi.movieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImdbConversionException extends RuntimeException {

    public ImdbConversionException(String message) {
        super(message);
    }
}
