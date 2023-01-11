package com.receitas.api.api.dto;

import com.receitas.api.api.entities.Endereco;
import com.receitas.api.api.entities.Usuario;

public record DadosDetalhamentoUsuario(Long id, String nome, String email, String senha, Endereco endereco) {
    public DadosDetalhamentoUsuario(Usuario user) {
        this(user.getId(), user.getNome(), user.getLogin(), user.getSenha(), user.getEndereco());
    }
}
