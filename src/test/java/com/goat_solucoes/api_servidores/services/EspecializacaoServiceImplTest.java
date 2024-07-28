package com.goat_solucoes.api_servidores.services;

import com.goat_solucoes.api_servidores.domain.especializacao.Especializacao;
import com.goat_solucoes.api_servidores.domain.enums.StatusEspecializacao;
import com.goat_solucoes.api_servidores.domain.servidor.Servidor;
import com.goat_solucoes.api_servidores.dto.request.especializacaoRequestDTO.EspecializacaoRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.especializacaoDTO.EspecializacaoResponseDTO;
import com.goat_solucoes.api_servidores.repositorios.EspecializacaoRepositorio;
import com.goat_solucoes.api_servidores.services.especializacaoService.EspecializacaoServiceImpl;
import com.goat_solucoes.api_servidores.services.mailService.EmailService;
import com.goat_solucoes.api_servidores.services.servidorServices.ServidorService;
import com.goat_solucoes.api_servidores.utils.especializacaoMapper.EspecializacaoMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class EspecializacaoServiceImplTest {

    @Mock
    private EmailService emailService;

    @Mock
    private ServidorService servidorService;

    @Mock
    private EspecializacaoRepositorio especializacaoRepositorio;

    @Mock
    private EspecializacaoMapper especializacaoMapper;

    @InjectMocks
    private EspecializacaoServiceImpl especializacaoService;

    @BeforeEach
    void setUp() {
        especializacaoService = new EspecializacaoServiceImpl(emailService,
                servidorService,
                especializacaoRepositorio,
                especializacaoMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Especializacao especializacao = new Especializacao();
        especializacao.setId(id);
        EspecializacaoResponseDTO dtoEsperado = new EspecializacaoResponseDTO(especializacao);
        dtoEsperado.setId(id);

        when(especializacaoRepositorio.findById(id)).thenReturn(Optional.of(especializacao));
        when(especializacaoMapper.toEspecializacaoDTO(especializacao)).thenReturn(dtoEsperado);

        EspecializacaoResponseDTO result = especializacaoService.findById(id);

        assertEquals(dtoEsperado, result);
        verify(especializacaoRepositorio).findById(id);
        verify(especializacaoMapper).toEspecializacaoDTO(especializacao);
    }

    @Test
    public void testCadastrar() {
        EspecializacaoRequestDTO reqDto = new EspecializacaoRequestDTO();
        Especializacao especializacao = new Especializacao();
        EspecializacaoResponseDTO dtoEsperado = new EspecializacaoResponseDTO(especializacao);

        when(especializacaoMapper.toEspecializacao(reqDto)).thenReturn(especializacao);
        when(especializacaoRepositorio.save(especializacao)).thenReturn(especializacao);
        when(especializacaoMapper.toEspecializacaoDTO(especializacao)).thenReturn(dtoEsperado);

        EspecializacaoResponseDTO result = especializacaoService.cadastrar(reqDto);

        assertEquals(dtoEsperado, result);
        verify(especializacaoRepositorio).save(especializacao);
        verify(especializacaoMapper).toEspecializacaoDTO(especializacao);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        EspecializacaoRequestDTO reqDto = new EspecializacaoRequestDTO();
        Especializacao especializacao = new Especializacao();
        EspecializacaoResponseDTO dtoEsperado = new EspecializacaoResponseDTO(especializacao);

        when(especializacaoRepositorio.findById(id)).thenReturn(Optional.of(especializacao));
        doNothing().when(especializacaoMapper).updateEspecializacao(especializacao, reqDto);
        when(especializacaoRepositorio.save(especializacao)).thenReturn(especializacao);
        when(especializacaoMapper.toEspecializacaoDTO(especializacao)).thenReturn(dtoEsperado);

        EspecializacaoResponseDTO result = especializacaoService.update(reqDto, id);

        assertEquals(dtoEsperado, result);
        verify(especializacaoRepositorio).findById(id);
        verify(especializacaoMapper).updateEspecializacao(especializacao, reqDto);
        verify(especializacaoRepositorio).save(especializacao);
        verify(especializacaoMapper).toEspecializacaoDTO(especializacao);
    }

    @Test
    public void testDeferir() {
        Long id = 1L;
        Especializacao especializacao = new Especializacao();
        especializacao.setId(id);
        especializacao.setStatus(StatusEspecializacao.PENDENTE);

        Servidor servidor = new Servidor();
        servidor.setEmail("test@example.com");
        servidor.setNome("Test User");
        especializacao.setServidor(servidor);

        EspecializacaoResponseDTO dtoEsperado = new EspecializacaoResponseDTO(especializacao);
        dtoEsperado.setId(id);

        when(especializacaoRepositorio.findById(id)).thenReturn(Optional.of(especializacao));
        when(especializacaoRepositorio.save(especializacao)).thenReturn(especializacao);
        when(especializacaoMapper.toEspecializacaoDTO(especializacao)).thenReturn(dtoEsperado);

        EspecializacaoResponseDTO result = especializacaoService.deferir(id);

        assertEquals(dtoEsperado, result);
        assertEquals(StatusEspecializacao.DEFERIDO, especializacao.getStatus());
        verify(especializacaoRepositorio).findById(id);
        verify(especializacaoRepositorio).save(especializacao);
        verify(especializacaoMapper).toEspecializacaoDTO(especializacao);
        verify(emailService).enviarEmail(anyString(), anyString(), anyString());
    }

    @Test
    public void testIndeferir() {
        Long id = 1L;
        String motivoIndeferimento = "Motivo Teste";
        Especializacao especializacao = new Especializacao();
        especializacao.setId(id);
        especializacao.setStatus(StatusEspecializacao.PENDENTE);

        Servidor servidor = new Servidor();
        servidor.setEmail("test@example.com");
        servidor.setNome("Test User");
        especializacao.setServidor(servidor);

        EspecializacaoResponseDTO dtoEsperado = new EspecializacaoResponseDTO(especializacao);
        dtoEsperado.setId(id);

        when(especializacaoRepositorio.findById(id)).thenReturn(Optional.of(especializacao));
        when(especializacaoRepositorio.save(especializacao)).thenReturn(especializacao);
        when(especializacaoMapper.toEspecializacaoDTO(especializacao)).thenReturn(dtoEsperado);

        EspecializacaoResponseDTO result = especializacaoService.indeferir(id, motivoIndeferimento);

        assertEquals(dtoEsperado, result);
        assertEquals(StatusEspecializacao.INDEFERIDO, especializacao.getStatus());
        assertEquals(motivoIndeferimento, especializacao.getMotivoIndeferimento());
        verify(especializacaoRepositorio).findById(id);
        verify(especializacaoRepositorio).save(especializacao);
        verify(especializacaoMapper).toEspecializacaoDTO(especializacao);
        verify(emailService).enviarEmail(anyString(), anyString(), anyString());
    }
}
