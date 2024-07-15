package com.goat_solucoes.api_servidores.services.servidorServices;

import com.goat_solucoes.api_servidores.dto.request.servidorRequestDTO.ServidorRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.servidorDTO.ServidorResponseDTO;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public interface ServidorService {

    ServidorResponseDTO findById(Long id);

    List<ServidorResponseDTO> findAll();

    ServidorResponseDTO cadastrar(ServidorRequestDTO reqServidorDTO) throws ConstraintViolationException;;

    ServidorResponseDTO update(ServidorRequestDTO servidorRequestDTO, Long id);

    String delete (Long id);
}
