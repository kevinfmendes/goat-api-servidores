package com.goat_solucoes.api_servidores.dto.response.servidorDTO;

import com.goat_solucoes.api_servidores.domain.enums.TipoServidor;
import com.goat_solucoes.api_servidores.domain.servidor.Servidor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ServidorResponseDTO {

    private Long id;

    private String cpf;

    private String email;

    private String matricula;

    private String nome;

    private LocalDate dataNascimento;

    private String sexo;

    private TipoServidor tipo;

    public ServidorResponseDTO(Servidor servidor){
        this.id = servidor.getId();
        this.cpf = servidor.getCpf();
        this.matricula = servidor.getMatricula();
        this.email = servidor.getEmail();
        this.nome = servidor.getNome();
        this.dataNascimento = servidor.getDataNascimento();
        this.sexo = servidor.getSexo();
        this.tipo = servidor.getTipo();

    }
}
