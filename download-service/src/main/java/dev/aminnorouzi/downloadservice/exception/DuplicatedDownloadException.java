package dev.aminnorouzi.downloadservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedDownloadException extends RuntimeException {

    public DuplicatedDownloadException(String message) {
        super(message);
    }
}
