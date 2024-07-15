package com.goat_solucoes.api_servidores.services.servidorServices;

import com.goat_solucoes.api_servidores.domain.servidor.Servidor;
import com.goat_solucoes.api_servidores.dto.request.servidorRequestDTO.ServidorRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.servidorDTO.ServidorResponseDTO;
import com.goat_solucoes.api_servidores.exceptions.CpfExistenteException;
import com.goat_solucoes.api_servidores.exceptions.EmailExistenteException;
import com.goat_solucoes.api_servidores.exceptions.MatriculaExistenteException;
import com.goat_solucoes.api_servidores.repositorios.ServidorRepositorio;
import com.goat_solucoes.api_servidores.utils.servidorMapper.ServidorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class ServidorServiceImpl implements ServidorService{

    private final ServidorRepositorio servidorRepositorio;

    private final ServidorMapper servidorMapper;

    @Override
    public ServidorResponseDTO findById(Long id) {
        return servidorMapper.toServidorDTO(returnServidor(id));
    }

    @Override
    public List<ServidorResponseDTO> findAll() {
        return servidorMapper.toServidorListDTO(servidorRepositorio.findAll());
    }

    @Override
    public ServidorResponseDTO cadastrar(ServidorRequestDTO reqServidorDTO) {

        validarServidor(reqServidorDTO);

        Servidor servidor = servidorMapper.toServidor(reqServidorDTO);
        return servidorMapper.toServidorDTO(servidorRepositorio.save(servidor));
    }

    @Override
    public ServidorResponseDTO update(ServidorRequestDTO servidorRequestDTO, Long id) {
        Servidor servidor = returnServidor(id);

        servidorMapper.updateServidor(servidor, servidorRequestDTO);

        return servidorMapper.toServidorDTO(servidorRepositorio.save(servidor));
    }

    @Override
    public String delete(Long id) {
        servidorRepositorio.deleteById(id);
        return "Servidor com id: "+id+" excluído";
    }

    private Servidor returnServidor(Long id){
        return servidorRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Servidor com id: "+id+ " não encontrado"));
    }

    private void validarServidor(ServidorRequestDTO reqServidorDTO) {
        if (servidorRepositorio.existsByCpf(reqServidorDTO.getCpf())) {
            throw new CpfExistenteException("CPF já cadastrado");
        }

        if (servidorRepositorio.existsByEmail(reqServidorDTO.getEmail())) {
            throw new EmailExistenteException("Email já cadastrado");
        }

        if (servidorRepositorio.existsByMatricula(reqServidorDTO.getMatricula())) {
            throw new MatriculaExistenteException("Matrícula já cadastrada");
        }
    }

}
