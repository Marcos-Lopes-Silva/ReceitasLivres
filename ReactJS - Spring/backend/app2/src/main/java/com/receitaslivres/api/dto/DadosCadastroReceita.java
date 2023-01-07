package com.receitaslivres.api.dto;

import com.receitaslivres.api.entities.User;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroReceita(@NotNull String modoPreparo, @NotNull String ingredientes, @NotNull User user) {

}
