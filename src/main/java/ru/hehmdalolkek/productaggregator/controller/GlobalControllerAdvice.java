package ru.hehmdalolkek.productaggregator.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller Advice to handle global errors.
 *
 * @author Inna Badekha
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, Object> errors = e.getConstraintViolations().stream()
                .collect(
                        HashMap::new,
                        (map, violation) -> map.put(violation.getPropertyPath().toString(), violation.getMessage()),
                        HashMap::putAll
                );

        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed.");
        problemDetail.setProperty("errors", errors);
        return ResponseEntity.badRequest()
                .body(problemDetail);
    }

}
