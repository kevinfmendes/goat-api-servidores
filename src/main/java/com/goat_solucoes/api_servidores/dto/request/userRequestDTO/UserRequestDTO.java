package com.goat_solucoes.api_servidores.dto.request.userRequestDTO;
import com.goat_solucoes.api_servidores.domain.enums.TipoUser;
import lombok.Getter;

@Getter
public class UserRequestDTO {
    private String login;
    private String password;
    private TipoUser tipo;
}
