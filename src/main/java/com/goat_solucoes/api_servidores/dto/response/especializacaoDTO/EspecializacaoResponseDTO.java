package com.goat_solucoes.api_servidores.dto.response.especializacaoDTO;

import com.goat_solucoes.api_servidores.domain.enums.StatusEspecializacao;
import com.goat_solucoes.api_servidores.domain.enums.TipoEspecializacao;
import com.goat_solucoes.api_servidores.domain.especializacao.Especializacao;
import com.goat_solucoes.api_servidores.dto.response.servidorDTO.ServidorResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EspecializacaoResponseDTO {

    private Long id;

    private String area;

    private TipoEspecializacao tipo;

    private int cargaHoraria;

    private double valorTotal;

    private ServidorResponseDTO servidor;

    private StatusEspecializacao status;

    private String motivoIndeferimento;

    public EspecializacaoResponseDTO(Especializacao especializacao){
        this.id = especializacao.getId();
        this.area = especializacao.getArea();
        this.tipo = especializacao.getTipo();
        this.cargaHoraria = especializacao.getCargaHoraria();
        this.valorTotal = especializacao.getValorTotal();
        this.servidor = especializacao.getServidor() != null ? new ServidorResponseDTO(especializacao.getServidor()) : null;
        this.status = especializacao.getStatus();
        this.motivoIndeferimento = especializacao.getMotivoIndeferimento();
    }

}
