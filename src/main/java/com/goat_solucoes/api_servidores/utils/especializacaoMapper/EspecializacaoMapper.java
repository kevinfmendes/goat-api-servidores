package com.goat_solucoes.api_servidores.utils.especializacaoMapper;

import com.goat_solucoes.api_servidores.domain.especializacao.Especializacao;
import com.goat_solucoes.api_servidores.domain.servidor.Servidor;
import com.goat_solucoes.api_servidores.dto.request.especializacaoRequestDTO.EspecializacaoRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.especializacaoDTO.EspecializacaoResponseDTO;
import com.goat_solucoes.api_servidores.repositorios.ServidorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EspecializacaoMapper {

    @Autowired
    private ServidorRepositorio servidorRepositorio;

    public Especializacao toEspecializacao(EspecializacaoRequestDTO especializacaoRequestDTO) {

        Servidor servidor = servidorRepositorio.findById(especializacaoRequestDTO.getServidorId())
                .orElseThrow(() -> new RuntimeException("Servidor not found"));

        return Especializacao.builder()
                .area(especializacaoRequestDTO.getArea())
                .cargaHoraria(especializacaoRequestDTO.getCargaHoraria())
                .tipo(especializacaoRequestDTO.getTipo())
                .valorTotal(especializacaoRequestDTO.getValorTotal())
                .servidor(servidor)
                .build();
    }

    public EspecializacaoResponseDTO toEspecializacaoDTO(Especializacao especializacao) {
        return new EspecializacaoResponseDTO(especializacao);
    }

    public List<EspecializacaoResponseDTO> toEspecializacaoListDTO(List<Especializacao> especializacaoList) {
        return especializacaoList.stream()
                .map(this::toEspecializacaoDTO)
                .collect(Collectors.toList());
    }

    public void updateEspecializacao(Especializacao especializacao, EspecializacaoRequestDTO especializacaoDTO) {
        especializacao.setArea(especializacaoDTO.getArea());
        especializacao.setTipo(especializacaoDTO.getTipo());
        especializacao.setCargaHoraria(especializacaoDTO.getCargaHoraria());
        especializacao.setValorTotal(especializacaoDTO.getValorTotal());

        Servidor servidor = servidorRepositorio.findById(especializacaoDTO.getServidorId())
                .orElseThrow(() -> new RuntimeException("Servidor not found"));

        especializacao.setServidor(servidor);
    }
}
