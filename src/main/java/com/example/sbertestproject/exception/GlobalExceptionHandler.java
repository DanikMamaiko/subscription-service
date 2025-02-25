package com.example.sbertestproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppException> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("Validation failed: {} errors found", ex.getBindingResult().getErrorCount());

        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());

        String errorMessage = String.join(", ", errors);
        log.warn("Validation errors: {}", errorMessage);

        AppException appException = new AppException(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(appException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<AppException> catchResourceNotFoundException(NoSuchElementException e) {
        log.error("Resource not found: {}", e.getMessage());
        return new ResponseEntity<>(new AppException(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
