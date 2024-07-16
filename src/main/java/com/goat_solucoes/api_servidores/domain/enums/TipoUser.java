package com.goat_solucoes.api_servidores.domain.enums;

public enum TipoUser {
    ADMIN("ROLE_ADMIN"),
    PADRAO("ROLE_USER");

    private final String role;

    TipoUser(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
