package com.goat_solucoes.api_servidores.exceptions;

public class MatriculaExistenteException extends RuntimeException{
    public MatriculaExistenteException (String message){
        super(message);
    }
}
