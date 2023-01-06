package com.receitas.api.api.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReceitaDto(String titulo, @NotNull List<String> ingredientes, @NotBlank String modoDePreparo,
        @NotNull Long clienteid) {

}
