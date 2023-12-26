package com.receitas.api.api.dto;

import com.receitas.api.api.entities.Categoria;
import com.receitas.api.api.entities.FileReference;
import com.receitas.api.api.entities.Receitas;

public record DadosListagemReceitas(Long id, String titulo, String descricao, FileReference photo, String size,
        int serve,
        Categoria categoria) {

    public DadosListagemReceitas(Receitas dados) {
        this(dados.getId(), dados.getTitulo(), dados.getDescricao(), dados.getPhoto(), dados.getSize(),
                dados.getServe(), dados.getCategoria());
    }
}