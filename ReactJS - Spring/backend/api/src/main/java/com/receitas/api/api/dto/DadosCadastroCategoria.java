package com.receitas.api.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCategoria(@NotBlank String nome) {

}
