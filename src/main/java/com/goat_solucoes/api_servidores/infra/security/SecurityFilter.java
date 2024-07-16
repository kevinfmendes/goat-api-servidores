package com.goat_solucoes.api_servidores.infra.security;

import com.goat_solucoes.api_servidores.domain.users.User;
import com.goat_solucoes.api_servidores.exceptions.FilterException;
import com.goat_solucoes.api_servidores.repositorios.UserRepositorio;
import com.goat_solucoes.api_servidores.services.tokenService.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepositorio userRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null && !token.equals("null")) {
            var login = tokenService.validateToken(token);
            Optional<User> userDetailsOptional = userRepositorio.findByLogin(login);

            if (userDetailsOptional.isPresent()) {
                User userDetails = userDetailsOptional.get();
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new FilterException(HttpStatus.UNAUTHORIZED, "401");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}


