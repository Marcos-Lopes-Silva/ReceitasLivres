package com.receitas.api.api.dto;

import com.receitas.api.api.entities.Usuario;

public record DadosListagemUser(Long id, String nome) {
    // public DadosListagemUser(Usuario user) {
    // this(user.getId(), user.getNome(), user.getLogin());
    // }
    public DadosListagemUser(Usuario user) {
        this(user.getId(), user.getNome());
    }
}
