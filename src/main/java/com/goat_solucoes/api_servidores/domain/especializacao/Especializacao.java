package com.goat_solucoes.api_servidores.domain.especializacao;

import com.goat_solucoes.api_servidores.domain.enums.StatusEspecializacao;
import com.goat_solucoes.api_servidores.domain.enums.TipoEspecializacao;
import com.goat_solucoes.api_servidores.domain.servidor.Servidor;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "especializacao")
@NoArgsConstructor
@Getter
@Setter
public class Especializacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEspecializacao tipo;

    @Column(nullable = false)
    private int cargaHoraria;

    @Column(nullable = false)
    private double valorTotal;

    @ManyToOne
    @JoinColumn(name = "servidor_id")
    private Servidor servidor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEspecializacao status = StatusEspecializacao.PENDENTE;

    private String motivoIndeferimento;

    @Builder
    public Especializacao(String area, TipoEspecializacao tipo, int cargaHoraria, double valorTotal, Servidor servidor) {
        this.area = area;
        this.tipo = tipo;
        this.cargaHoraria = cargaHoraria;
        this.valorTotal = valorTotal;
        this.servidor = servidor;
    }

}
