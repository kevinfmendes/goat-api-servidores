package com.goat_solucoes.api_servidores.services.especializacaoService;

import com.goat_solucoes.api_servidores.domain.enums.StatusEspecializacao;
import com.goat_solucoes.api_servidores.domain.especializacao.Especializacao;
import com.goat_solucoes.api_servidores.dto.request.especializacaoRequestDTO.EspecializacaoRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.especializacaoDTO.EspecializacaoResponseDTO;
import com.goat_solucoes.api_servidores.repositorios.EspecializacaoRepositorio;
import com.goat_solucoes.api_servidores.services.mailService.EmailService;
import com.goat_solucoes.api_servidores.services.servidorServices.ServidorService;
import com.goat_solucoes.api_servidores.utils.especializacaoMapper.EspecializacaoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class EspecializacaoServiceImpl implements EspecializacaoService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ServidorService servidorService;

    private final EspecializacaoRepositorio especializacaoRepositorio;

    private final EspecializacaoMapper especializacaoMapper;

    @Override
    public EspecializacaoResponseDTO findById(Long id) {
        return especializacaoMapper.toEspecializacaoDTO(returnEspecializacao(id));
    }

    @Override
    public List<EspecializacaoResponseDTO> findAll() {
        return especializacaoMapper.toEspecializacaoListDTO(especializacaoRepositorio.findAll());
    }

    @Override
    public EspecializacaoResponseDTO cadastrar(EspecializacaoRequestDTO reEspecializacaoDTO) {
        Especializacao especializacao = especializacaoMapper.toEspecializacao(reEspecializacaoDTO);
        return especializacaoMapper.toEspecializacaoDTO(especializacaoRepositorio.save(especializacao));
    }

    @Override
    public EspecializacaoResponseDTO update(EspecializacaoRequestDTO especializacaoRequestDTO, Long id) {
        Especializacao especializacao = returnEspecializacao(id);
        especializacaoMapper.updateEspecializacao(especializacao, especializacaoRequestDTO);

        return especializacaoMapper.toEspecializacaoDTO(especializacaoRepositorio.save(especializacao));
    }

    @Override
    public String delete(Long id) {
        especializacaoRepositorio.deleteById(id);
        return "Especialização com id: "+id+" excluído";
    }

    @Transactional
    public EspecializacaoResponseDTO deferir(Long id) {
        Especializacao especializacao = returnEspecializacao(id);
        especializacao.setStatus(StatusEspecializacao.DEFERIDO);
        Especializacao savedEspecializacao = especializacaoRepositorio.save(especializacao);
        EspecializacaoResponseDTO responseDTO = especializacaoMapper.toEspecializacaoDTO(savedEspecializacao);

        String emailDestinatario = especializacao.getServidor().getEmail();
        String nomeDestinario = especializacao.getServidor().getNome();

        enviarEmailDeDeferimento(emailDestinatario, nomeDestinario);

        return responseDTO;
    }

    @Transactional
    public EspecializacaoResponseDTO indeferir(Long id, String motivoIndeferimento) {
        Especializacao especializacao = returnEspecializacao(id);
        especializacao.setStatus(StatusEspecializacao.INDEFERIDO);
        especializacao.setMotivoIndeferimento(motivoIndeferimento);
        Especializacao savedEspecializacao = especializacaoRepositorio.save(especializacao);
        EspecializacaoResponseDTO responseDTO = especializacaoMapper.toEspecializacaoDTO(savedEspecializacao);

        String emailDestinatario = especializacao.getServidor().getEmail();
        String nomeDestinario = especializacao.getServidor().getNome();

        enviarEmailDeIndeferimento(emailDestinatario, nomeDestinario, motivoIndeferimento);

        return responseDTO;
    }

    private Especializacao returnEspecializacao(Long id){
        return especializacaoRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("Especializacao com id: "+id+ " não encontrado"));
    }

    private void enviarEmailDeDeferimento(String emailDestinatario, String nomeDestinario) {
        String assunto = "Especialização Deferida";
        String mensagem = "Olá, "+nomeDestinario+"! Sua especialização foi deferida com sucesso.";

        emailService.enviarEmail(emailDestinatario, assunto, mensagem);
    }

    private void enviarEmailDeIndeferimento(String emailDestinatario, String nomeDestinario, String motivoIndeferimento) {
        String assunto = "Especialização Indeferida";
        String mensagem = "Olá, "+nomeDestinario+". Sua especialização foi indeferida. Motivo: "+motivoIndeferimento;

        emailService.enviarEmail(emailDestinatario, assunto, mensagem);
    }
}
