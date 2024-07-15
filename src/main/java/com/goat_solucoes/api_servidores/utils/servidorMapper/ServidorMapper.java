package com.goat_solucoes.api_servidores.utils.servidorMapper;

import com.goat_solucoes.api_servidores.domain.servidor.Servidor;
import com.goat_solucoes.api_servidores.dto.request.servidorRequestDTO.ServidorRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.servidorDTO.ServidorResponseDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServidorMapper {

    public Servidor toServidor(ServidorRequestDTO servidorRequestDTO){
        return Servidor.builder()
                .cpf(servidorRequestDTO.getCpf())
                .email(servidorRequestDTO.getEmail())
                .matricula(servidorRequestDTO.getMatricula())
                .nome(servidorRequestDTO.getNome())
                .dataNascimento(servidorRequestDTO.getDataNascimento())
                .sexo(servidorRequestDTO.getSexo())
                .tipo(servidorRequestDTO.getTipo())
                .build();
    }

    public ServidorResponseDTO toServidorDTO(Servidor servidor){
        return new ServidorResponseDTO(servidor);
    }

    public List<ServidorResponseDTO> toServidorListDTO(List<Servidor> servidorList){
        return servidorList.stream().map(ServidorResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void updateServidor(Servidor servidor, ServidorRequestDTO servidorDTO){
        servidor.setCpf(servidorDTO.getCpf());
        servidor.setEmail(servidorDTO.getEmail());
        servidor.setMatricula(servidorDTO.getMatricula());
        servidor.setNome(servidorDTO.getNome());
        servidor.setDataNascimento(servidorDTO.getDataNascimento());
        servidor.setSexo(servidorDTO.getSexo());
        servidor.setTipo(servidorDTO.getTipo());
    }

}
