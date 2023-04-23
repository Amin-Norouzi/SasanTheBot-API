package dev.aminnorouzi.userservice.advice;

import dev.aminnorouzi.userservice.exception.IllegalUserRequestException;
import dev.aminnorouzi.userservice.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserControllerAdvice {

    private static final String ERROR_MESSAGE = "Caught new error: {}";

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<Error> handleGeneralException(Exception exception) {
        Error error = new Error();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(exception.getMessage());

        log.error(ERROR_MESSAGE, error);
        return new ResponseEntity<>(error, HttpStatus.FAILED_DEPENDENCY);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> handleUserNotFoundException(UserNotFoundException exception) {
        Error error = new Error();
        error.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());

        log.error(ERROR_MESSAGE, error);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalUserRequestException.class)
    public ResponseEntity<Error> handleIllegalUserRequestException(IllegalUserRequestException exception) {
        Error error = new Error();
        error.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());

        log.error(ERROR_MESSAGE, error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Error {

        private String status;
        private Integer code;
        private String message;
    }
}
