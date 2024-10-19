package ru.hehmdalolkek.productaggregator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.hehmdalolkek.productaggregator.exception.ProductIsAlreadyExistsException;
import ru.hehmdalolkek.productaggregator.exception.ProductNotFoundException;

/**
 * Controller Advice to handle <code>Product</code> errors.
 *
 * @author Inna Badekha
 */
@RestControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductIsAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> clientIsAlreadyExistsException(ProductIsAlreadyExistsException e) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.badRequest()
                .body(problemDetail);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProblemDetail> clientNotFoundException(ProductNotFoundException e) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problemDetail);
    }

}
