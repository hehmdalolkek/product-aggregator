package ru.hehmdalolkek.productaggregator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.hehmdalolkek.productaggregator.exception.ClientIsAlreadyExistsException;
import ru.hehmdalolkek.productaggregator.exception.ClientNotFoundException;

/**
 * Controller Advice to handle <code>Client</code> errors.
 *
 * @author Inna Badekha
 */
@RestControllerAdvice
public class ClientControllerAdvice {

    @ExceptionHandler(ClientIsAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> clientIsAlreadyExistsException(ClientIsAlreadyExistsException e) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.badRequest()
                .body(problemDetail);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ProblemDetail> clientNotFoundException(ClientNotFoundException e) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problemDetail);
    }

}
