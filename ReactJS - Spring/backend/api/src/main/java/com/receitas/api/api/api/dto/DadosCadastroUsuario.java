package com.receitas.api.api.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(@NotBlank @Email String login, @NotBlank String senha,
                @NotNull @Valid DadosCadastroEndereco endereco, @NotBlank String nome, Long photoId) {

}
