package com.receitaslivres.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosPerfil(@NotBlank String nome) {

}
