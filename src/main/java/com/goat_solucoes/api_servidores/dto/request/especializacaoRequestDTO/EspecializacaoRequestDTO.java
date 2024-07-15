package com.goat_solucoes.api_servidores.dto.request.especializacaoRequestDTO;

import com.goat_solucoes.api_servidores.domain.enums.TipoEspecializacao;
import lombok.Getter;

@Getter
public class EspecializacaoRequestDTO {

    private String area;

    private TipoEspecializacao tipo;

    private int cargaHoraria;

    private double valorTotal;

    private Long servidorId;

}
