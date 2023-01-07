package com.receitaslivres.api.dto;

import java.util.List;

import com.receitaslivres.api.entities.Endereco;
import com.receitaslivres.api.entities.Receitas;
import com.receitaslivres.api.entities.TiposUser;
import com.receitaslivres.api.entities.User;

public record DadosDetalhamentoUser(Long id, String nome, String email, String senha, Endereco endereco,
        List<TiposUser> tipo,
        List<Receitas> receitas) {

    public DadosDetalhamentoUser(User user) {
        this(user.getId(), user.getNome(), user.getEmail(), user.getSenha(), user.getEndereco(), user.getTipo(),
                user.getUserReceitas());
    }

}
