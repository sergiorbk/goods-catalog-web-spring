package com.sergosoft.goodscatalog.exception;

public class EntityUniqueViolationException extends RuntimeException {

    public EntityUniqueViolationException(String message) {
        super(message);
    }
}
