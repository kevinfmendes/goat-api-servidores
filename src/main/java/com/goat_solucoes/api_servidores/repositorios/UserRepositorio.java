package com.goat_solucoes.api_servidores.repositorios;

import com.goat_solucoes.api_servidores.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepositorio extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

}
