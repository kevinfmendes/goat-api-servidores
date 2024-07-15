package com.goat_solucoes.api_servidores.domain.servidor;
import com.goat_solucoes.api_servidores.domain.enums.TipoServidor;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="servidor")
@NoArgsConstructor
@Getter
@Setter
public class Servidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoServidor tipo;

    @Builder
    public Servidor(String cpf, String email, String matricula, String nome, LocalDate dataNascimento, String sexo, TipoServidor tipo) {
        this.cpf = cpf;
        this.email = email;
        this.matricula = matricula;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.tipo = tipo;
    }

}
