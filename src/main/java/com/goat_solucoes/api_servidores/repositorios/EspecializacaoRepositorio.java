package com.goat_solucoes.api_servidores.repositorios;

import com.goat_solucoes.api_servidores.domain.especializacao.Especializacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecializacaoRepositorio extends JpaRepository<Especializacao, Long> {
}
