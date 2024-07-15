package com.goat_solucoes.api_servidores.exceptions;

public class CpfExistenteException extends RuntimeException {
    public CpfExistenteException(String message) {
        super(message);
    }
}