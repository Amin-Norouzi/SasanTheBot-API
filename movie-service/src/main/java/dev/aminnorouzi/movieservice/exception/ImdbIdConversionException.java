package dev.aminnorouzi.movieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImdbIdConversionException extends RuntimeException {

    public ImdbIdConversionException(String message) {
        super(message);
    }
}
