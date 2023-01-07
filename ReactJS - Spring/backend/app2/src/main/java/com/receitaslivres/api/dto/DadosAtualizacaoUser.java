package com.receitaslivres.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUser(@NotNull Long id, String nome, String email, String senha, DadosEndereco endereco) {

}
