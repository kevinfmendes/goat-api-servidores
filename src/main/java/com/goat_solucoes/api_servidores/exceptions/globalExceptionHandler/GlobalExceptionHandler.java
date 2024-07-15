package com.goat_solucoes.api_servidores.exceptions.globalExceptionHandler;

import com.goat_solucoes.api_servidores.exceptions.CpfExistenteException;
import com.goat_solucoes.api_servidores.exceptions.EmailExistenteException;
import com.goat_solucoes.api_servidores.exceptions.MatriculaExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CpfExistenteException.class)
    public ResponseEntity<String> handleCpfAlreadyExistsException(CpfExistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(EmailExistenteException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailExistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(MatriculaExistenteException.class)
    public ResponseEntity<String> handleMatriculaAlreadyExistsException(MatriculaExistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
    }
}
