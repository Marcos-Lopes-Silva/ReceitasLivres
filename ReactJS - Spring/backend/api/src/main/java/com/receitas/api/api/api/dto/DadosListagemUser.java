package com.receitas.api.api.api.dto;

import com.receitas.api.api.domain.entities.Usuario;

public record DadosListagemUser(Long id, String nome, Long photo) {

    public DadosListagemUser(Usuario user) {
        this(user.getId(), user.getNome(), user.getPhoto().getId());
    }
}
