package com.receitaslivres.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUser(@NotBlank @Email String email, @NotBlank String senha,
        @NotNull @Valid DadosEndereco endereco, @NotBlank @NotNull String nome, @NotBlank String tipo) {

}
