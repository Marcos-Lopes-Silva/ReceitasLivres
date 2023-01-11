package com.receitas.api.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroReceitas(@NotBlank String modoPreparo, @NotBlank String ingredientes) {

}
