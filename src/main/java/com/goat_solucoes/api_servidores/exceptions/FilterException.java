package com.goat_solucoes.api_servidores.exceptions;

import org.springframework.http.HttpStatus;

public class FilterException extends RuntimeException{
    private final String message;
    private final HttpStatus status;

    public FilterException(HttpStatus status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
