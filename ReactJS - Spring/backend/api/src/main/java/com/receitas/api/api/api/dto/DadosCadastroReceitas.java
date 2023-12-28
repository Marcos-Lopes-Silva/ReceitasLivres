package com.receitas.api.api.api.dto;

import com.receitas.api.api.domain.entities.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroReceitas(@NotBlank String titulo, @NotBlank String modoPreparo,
        @NotBlank String ingredientes, Usuario usuario, @NotBlank String descricao, @NotNull Long photoId,
        Long categoriaId, @NotNull int serve, @NotBlank String size) {

}
