package com.goat_solucoes.api_servidores.services.especializacaoService;


import com.goat_solucoes.api_servidores.dto.request.especializacaoRequestDTO.EspecializacaoRequestDTO;
import com.goat_solucoes.api_servidores.dto.request.servidorRequestDTO.ServidorRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.especializacaoDTO.EspecializacaoResponseDTO;

import java.util.List;

public interface EspecializacaoService {
    EspecializacaoResponseDTO findById(Long id);

    List<EspecializacaoResponseDTO> findAll();

    EspecializacaoResponseDTO cadastrar(EspecializacaoRequestDTO reEspecializacaoDTO);

    EspecializacaoResponseDTO update(EspecializacaoRequestDTO especializacaoRequestDTO, Long id);

    String delete (Long id);

    EspecializacaoResponseDTO deferir(Long id);

    EspecializacaoResponseDTO indeferir(Long id, String motivoIndeferimento);

}
