package com.goat_solucoes.api_servidores.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.goat_solucoes.api_servidores.domain.servidor.Servidor;
import com.goat_solucoes.api_servidores.dto.request.servidorRequestDTO.ServidorRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.servidorDTO.ServidorResponseDTO;
import com.goat_solucoes.api_servidores.exceptions.CpfExistenteException;
import com.goat_solucoes.api_servidores.repositorios.ServidorRepositorio;
import com.goat_solucoes.api_servidores.services.servidorServices.ServidorServiceImpl;
import com.goat_solucoes.api_servidores.utils.servidorMapper.ServidorMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServidorServiceImplTest {

    @Mock
    private ServidorRepositorio servidorRepositorio;

    @Mock
    private ServidorMapper servidorMapper;

    @InjectMocks
    private ServidorServiceImpl servidorService;

    @BeforeEach
    void setUp() {
        servidorService = new ServidorServiceImpl(servidorRepositorio, servidorMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Servidor servidor = new Servidor();
        servidor.setId(id);
        ServidorResponseDTO expectedDto = new ServidorResponseDTO(servidor);
        expectedDto.setId(id);

        when(servidorRepositorio.findById(id)).thenReturn(Optional.of(servidor));
        when(servidorMapper.toServidorDTO(servidor)).thenReturn(expectedDto);

        ServidorResponseDTO result = servidorService.findById(id);

        assertEquals(expectedDto, result);
        verify(servidorRepositorio).findById(id);
        verify(servidorMapper).toServidorDTO(servidor);
    }

    @Test
    public void testCadastrar() {
        ServidorRequestDTO reqDto = new ServidorRequestDTO();
        reqDto.setCpf("12345678901");
        reqDto.setEmail("test@example.com");
        reqDto.setMatricula("12345");

        Servidor servidor = new Servidor();
        ServidorResponseDTO expectedDto = new ServidorResponseDTO(servidor);

        when(servidorRepositorio.existsByCpf(anyString())).thenReturn(false);
        when(servidorRepositorio.existsByEmail(anyString())).thenReturn(false);
        when(servidorRepositorio.existsByMatricula(anyString())).thenReturn(false);
        when(servidorMapper.toServidor(reqDto)).thenReturn(servidor);
        when(servidorRepositorio.save(servidor)).thenReturn(servidor);
        when(servidorMapper.toServidorDTO(servidor)).thenReturn(expectedDto);

        ServidorResponseDTO result = servidorService.cadastrar(reqDto);

        assertEquals(expectedDto, result);
        verify(servidorRepositorio).save(servidor);
        verify(servidorMapper).toServidorDTO(servidor);
    }

    @Test
    public void testCadastrarCpfExistente() {
        ServidorRequestDTO reqDto = new ServidorRequestDTO();
        reqDto.setCpf("12345678901");

        when(servidorRepositorio.existsByCpf(reqDto.getCpf())).thenReturn(true);

        assertThrows(CpfExistenteException.class, () -> servidorService.cadastrar(reqDto));
        verify(servidorRepositorio, never()).save(any(Servidor.class));
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        ServidorRequestDTO reqDto = new ServidorRequestDTO();
        Servidor servidor = new Servidor();
        ServidorResponseDTO expectedDto = new ServidorResponseDTO(servidor);

        when(servidorRepositorio.findById(id)).thenReturn(Optional.of(servidor));
        doNothing().when(servidorMapper).updateServidor(servidor, reqDto);
        when(servidorRepositorio.save(servidor)).thenReturn(servidor);
        when(servidorMapper.toServidorDTO(servidor)).thenReturn(expectedDto);

        ServidorResponseDTO result = servidorService.update(reqDto, id);

        assertEquals(expectedDto, result);
        verify(servidorRepositorio).findById(id);
        verify(servidorMapper).updateServidor(servidor, reqDto);
        verify(servidorRepositorio).save(servidor);
        verify(servidorMapper).toServidorDTO(servidor);
    }
}
