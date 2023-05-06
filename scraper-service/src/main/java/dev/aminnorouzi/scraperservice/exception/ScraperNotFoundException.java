package dev.aminnorouzi.scraperservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ScraperNotFoundException extends RuntimeException {

    public ScraperNotFoundException(String message) {
        super(message);
    }
}
