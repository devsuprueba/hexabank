package com.hexabank.client.application.exception;

public class DuplicateIdentificationException extends RuntimeException {
    public DuplicateIdentificationException(String message) {
        super(message);
    }
}
