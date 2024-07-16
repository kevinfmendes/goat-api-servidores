package com.goat_solucoes.api_servidores.controllers;

import com.goat_solucoes.api_servidores.domain.users.User;
import com.goat_solucoes.api_servidores.dto.request.userRequestDTO.UserRequestDTO;
import com.goat_solucoes.api_servidores.dto.response.tokenResponse.TokenResponse;
import com.goat_solucoes.api_servidores.dto.response.usuarioResponseDTO.UsuarioResponseDTO;
import com.goat_solucoes.api_servidores.repositorios.UserRepositorio;
import com.goat_solucoes.api_servidores.services.tokenService.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepositorio userRepositorio;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequestDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());

            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer " + token);

            return ResponseEntity.ok().headers(header).body(token);
        } catch (AuthenticationException e) {
            logger.error("Autenticação falhou", e);
            return ResponseEntity.status(401).body("Usuário ou senha incorretos"); // Unauthorized
        } catch (Exception e) {
            logger.error("An error occurred during login", e);
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequestDTO data){
        if(this.userRepositorio.findByLogin(data.getLogin()).isPresent()) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        User newUser = new User(data.getLogin(), encryptedPassword, data.getTipo());
        this.userRepositorio.save(newUser);

        return ResponseEntity.ok().body("Usuário criado com sucesso");
    }

}
