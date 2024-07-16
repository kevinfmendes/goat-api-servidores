package com.goat_solucoes.api_servidores.infra.security;

import com.goat_solucoes.api_servidores.domain.users.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static UserDetails getAuthenticatedUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getAuthenticatedUserId() {
        UserDetails userDetails = getAuthenticatedUser();
        User usuarioLogado = (User) userDetails;
        return usuarioLogado.getId();
    }

}