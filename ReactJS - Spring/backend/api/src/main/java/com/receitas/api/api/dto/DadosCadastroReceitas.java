package com.receitas.api.api.dto;

import com.receitas.api.api.entities.Categoria;
import com.receitas.api.api.entities.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroReceitas(@NotBlank String titulo, @NotBlank String modoPreparo,
        @NotBlank String ingredientes, Usuario usuario, @NotBlank String descricao, String urlImage,
        Categoria categoria, @NotNull int serve, @NotBlank String size) {

}
