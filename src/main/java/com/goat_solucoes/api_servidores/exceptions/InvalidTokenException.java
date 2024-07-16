package com.goat_solucoes.api_servidores.exceptions;

public class InvalidTokenException extends RuntimeException  {
    public InvalidTokenException(String message) {
        super(message);
    }
    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}