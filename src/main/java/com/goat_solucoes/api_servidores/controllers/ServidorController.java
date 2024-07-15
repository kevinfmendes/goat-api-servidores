package com.goat_solucoes.api_servidores.controllers;
import com.goat_solucoes.api_servidores.dto.request.servidorRequestDTO.ServidorRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.servidorDTO.ServidorResponseDTO;
import com.goat_solucoes.api_servidores.services.servidorServices.ServidorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/servidores")
@RequiredArgsConstructor
public class ServidorController {

    private final ServidorService servidorService;

    @GetMapping("/{id}")
    public ResponseEntity<ServidorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(servidorService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServidorResponseDTO>> findAll() {
        return ResponseEntity.ok().body(servidorService.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ServidorResponseDTO> cadastrar(@RequestBody ServidorRequestDTO servidorRequestDTO,
                                                         UriComponentsBuilder uriBuilder) {
        ServidorResponseDTO servidorResponseDTO = servidorService.cadastrar(servidorRequestDTO);
        URI uri = uriBuilder.path("/servidores/{id}").buildAndExpand(servidorResponseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(servidorResponseDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ServidorResponseDTO> update(@RequestBody ServidorRequestDTO servidorRequestDTO,
                                                      @PathVariable Long id) {
        return ResponseEntity.ok().body(servidorService.update(servidorRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(servidorService.delete(id));
    }

}
