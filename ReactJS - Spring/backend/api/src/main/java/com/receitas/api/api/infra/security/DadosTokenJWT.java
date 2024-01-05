package com.receitas.api.api.infra.security;

public record DadosTokenJWT(String token, String nome, String photo, Long id) {
}
