package com.goat_solucoes.api_servidores.services.tokenService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.goat_solucoes.api_servidores.domain.users.User;
import com.goat_solucoes.api_servidores.exceptions.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token}")
    private String secret;

    public String generateToken(User usuario) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("sysled-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("sysled-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException exception){
            return "";
        }
    }

    public String getClaimsFromToken(String token) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("sysled-api")
                    .build();

            return verifier.verify(token).getId();

        } catch (JWTVerificationException e) {
            throw new InvalidTokenException("Token inválido", e);
        }
    }

    public String replaceToken(String token) {
        var tokenRecebido = token;
        String[] parts = tokenRecebido.split(":");

        if (parts.length > 1) {
            String tokenAtt = parts[1].trim();
            tokenAtt = tokenAtt.substring(0, tokenAtt.length() -1);
            tokenAtt = tokenAtt.substring(1, tokenAtt.length() -1);
            return tokenAtt;
        }

        return "";
    }

    //definindo o tempo de expiração do token jwt
    public Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(10).toInstant(ZoneOffset.of("-03:00"));
    }

}
