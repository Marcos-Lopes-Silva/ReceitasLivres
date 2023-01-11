package com.receitas.api.api.dto;

import com.receitas.api.api.entities.Receitas;

public record DadosDetalhamentoReceita(Long id, String modoPreparo, String ingredientes, String nomeUsuario) {
    public DadosDetalhamentoReceita(Receitas dados) {
        this(dados.getId(), dados.getModoPreparo(), dados.getIngredientes(), dados.getUsuario().getNome());
    }
}
