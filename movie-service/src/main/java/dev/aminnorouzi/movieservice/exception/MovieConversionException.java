package dev.aminnorouzi.movieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MovieConversionException extends RuntimeException {

    public MovieConversionException(String message) {
        super(message);
    }
}
