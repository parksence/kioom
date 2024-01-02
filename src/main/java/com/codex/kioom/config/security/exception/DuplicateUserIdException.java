package com.codex.kioom.config.security.exception;

public class DuplicateUserIdException extends RuntimeException {

    public DuplicateUserIdException(String message) {
        super(message);
    }
}
