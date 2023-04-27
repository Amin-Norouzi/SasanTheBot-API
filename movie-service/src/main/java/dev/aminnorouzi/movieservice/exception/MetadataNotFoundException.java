package dev.aminnorouzi.movieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MetadataNotFoundException extends RuntimeException {

    public MetadataNotFoundException(String message) {
        super(message);
    }
}
