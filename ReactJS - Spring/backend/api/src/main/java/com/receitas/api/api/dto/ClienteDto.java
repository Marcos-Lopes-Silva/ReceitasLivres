package com.receitas.api.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteDto(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String senha,
        @NotNull @Valid EnderecoDto endereco, @NotNull @Pattern(regexp = "\\d{11}") String cpf) {

}
