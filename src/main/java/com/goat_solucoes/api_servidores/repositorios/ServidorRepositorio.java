package com.goat_solucoes.api_servidores.repositorios;

import com.goat_solucoes.api_servidores.domain.servidor.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServidorRepositorio extends JpaRepository<Servidor, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByMatricula(String matricula);

}
