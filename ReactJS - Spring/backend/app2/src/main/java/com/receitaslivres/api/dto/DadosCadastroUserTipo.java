package com.receitaslivres.api.dto;

import com.receitaslivres.api.entities.TiposUser;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUserTipo(@NotBlank @Email String email, @NotBlank String senha,
        @NotNull @Valid DadosEndereco endereco, @NotBlank @NotNull String nome, @NotNull TiposUser tipo) {

    public DadosCadastroUserTipo(DadosCadastroUser user, TiposUser tiposUser) {
        this(user.senha(), user.nome(), user.endereco(), user.email(), tiposUser);
    }

}
