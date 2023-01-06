package com.receitas.api.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDto(@NotBlank String cidade, @NotBlank String uf,
                @NotBlank String logradouro, String numero, @NotBlank @Pattern(regexp = "\\d{8}") String cep,
                @NotBlank String bairro, String complemento) {

}
