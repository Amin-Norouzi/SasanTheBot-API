package dev.aminnorouzi.downloadservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DownloadNotFoundException extends RuntimeException {

    public DownloadNotFoundException(String message) {
        super(message);
    }
}
