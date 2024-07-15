package com.goat_solucoes.api_servidores.services.especializacaoService;

import com.goat_solucoes.api_servidores.domain.enums.StatusEspecializacao;
import com.goat_solucoes.api_servidores.domain.especializacao.Especializacao;
import com.goat_solucoes.api_servidores.dto.request.especializacaoRequestDTO.EspecializacaoRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.especializacaoDTO.EspecializacaoResponseDTO;
import com.goat_solucoes.api_servidores.repositorios.EspecializacaoRepositorio;
import com.goat_solucoes.api_servidores.utils.especializacaoMapper.EspecializacaoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class EspecializacaoServiceImpl implements EspecializacaoService {

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
        return especializacaoMapper.toEspecializacaoDTO(especializacaoRepositorio.save(especializacao));
    }

    @Transactional
    public EspecializacaoResponseDTO indeferir(Long id, String motivoIndeferimento) {
        Especializacao especializacao = returnEspecializacao(id);
        especializacao.setStatus(StatusEspecializacao.INDEFERIDO);
        especializacao.setMotivoIndeferimento(motivoIndeferimento);
        return especializacaoMapper.toEspecializacaoDTO(especializacaoRepositorio.save(especializacao));
    }

    private Especializacao returnEspecializacao(Long id){
        return especializacaoRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("Especializacao com id: "+id+ " não encontrado"));
    }
}
