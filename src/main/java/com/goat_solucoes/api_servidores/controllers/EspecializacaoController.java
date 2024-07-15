package com.goat_solucoes.api_servidores.controllers;

import com.goat_solucoes.api_servidores.dto.request.especializacaoRequestDTO.EspecializacaoRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.especializacaoDTO.EspecializacaoResponseDTO;
import com.goat_solucoes.api_servidores.dto.response.motivoIndeferimentoDTO.MotivoIndeferimentoDTO;
import com.goat_solucoes.api_servidores.services.especializacaoService.EspecializacaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/especializacoes")
@RequiredArgsConstructor
public class EspecializacaoController {

    private final EspecializacaoService especializacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<EspecializacaoResponseDTO> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(especializacaoService.findById(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EspecializacaoResponseDTO>> findAll(){
        try{
            return ResponseEntity.ok().body(especializacaoService.findAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EspecializacaoResponseDTO> cadastrar(@RequestBody EspecializacaoRequestDTO especializacaoRequestDTO,
                                                               UriComponentsBuilder uriBuilder){
      try{
          EspecializacaoResponseDTO especializacaoResponseDTO = especializacaoService.cadastrar(especializacaoRequestDTO);

          URI uri = uriBuilder.path("/api/v1/especializacoes/{id}").buildAndExpand(especializacaoResponseDTO.getId()).toUri();

          return ResponseEntity.created(uri).body(especializacaoResponseDTO);

      } catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EspecializacaoResponseDTO> update (@RequestBody EspecializacaoRequestDTO especializacaoRequestDTO,
                                                             @PathVariable Long id){
        try{
            return ResponseEntity.ok().body(especializacaoService.update(especializacaoRequestDTO, id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(especializacaoService.delete(id));
    }

    @PutMapping("/deferir/{id}")
    public ResponseEntity<EspecializacaoResponseDTO> deferir(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(especializacaoService.deferir(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/indeferir/{id}")
    public ResponseEntity<EspecializacaoResponseDTO> indeferir(@PathVariable Long id, @RequestBody MotivoIndeferimentoDTO motivoIndeferimentoDTO) {
        try {
            return ResponseEntity.ok().body(especializacaoService.indeferir(id, motivoIndeferimentoDTO.getMotivoIndeferimento()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
