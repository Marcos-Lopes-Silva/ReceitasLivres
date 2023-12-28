package com.receitas.api.api.api.dto;

import com.receitas.api.api.domain.entities.Categoria;
import com.receitas.api.api.domain.entities.FileReference;
import com.receitas.api.api.domain.entities.Receita;

public record DadosListagemReceitas(Long id, String titulo, String descricao, FileReference photo, String size,
        int serve,
        Categoria categoria) {

    public DadosListagemReceitas(Receita dados) {
        this(dados.getId(), dados.getTitulo(), dados.getDescricao(), dados.getPhoto(), dados.getSize(),
                dados.getServe(), dados.getCategoria());
    }
}