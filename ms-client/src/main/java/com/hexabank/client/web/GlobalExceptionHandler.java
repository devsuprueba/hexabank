package com.hexabank.client.web;

import com.hexabank.client.application.exception.DuplicateIdentificationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// ControllerAdvice disabled: project already provides a central exception handler in
// `com.hexabank.client.infrastructure.controller.advice.GlobalExceptionHandler`.
// This class is kept for local custom messages but should not be registered as a bean.
public class GlobalExceptionHandler {

    private static final String ERROR_KEY = "error";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            errors.put(fe.getField(), fe.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DuplicateIdentificationException.class)
    public ResponseEntity<Map<String, String>> handleDuplicate(DuplicateIdentificationException ex) {
        Map<String, String> body = new HashMap<>();
        body.put(ERROR_KEY, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(IllegalArgumentException ex) {
        Map<String, String> body = new HashMap<>();
        body.put(ERROR_KEY, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException ex) {
        Map<String, String> body = new HashMap<>();
        body.put(ERROR_KEY, "data integrity violation");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}
