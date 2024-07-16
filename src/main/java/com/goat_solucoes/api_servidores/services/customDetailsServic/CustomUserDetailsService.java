package com.goat_solucoes.api_servidores.services.customDetailsServic;

import com.goat_solucoes.api_servidores.repositorios.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositorio userRepositorio;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepositorio.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with login: " + login));
    }
}
