package com.goat_solucoes.api_servidores.dto.request.servidorRequestDTO;

import com.goat_solucoes.api_servidores.domain.enums.TipoServidor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ServidorRequestDTO {

    private String cpf;

    private String email;

    private String matricula;

    private String nome;

    private LocalDate dataNascimento;

    private String sexo;

    private TipoServidor tipo;

}
