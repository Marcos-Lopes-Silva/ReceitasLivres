package com.receitas.api.api.api.dto;

import java.util.List;

import com.receitas.api.api.domain.entities.Categoria;
import com.receitas.api.api.domain.entities.Receita;

public record DadosCategoria(Long id, String nome, List<Receita> receitas) {
    public DadosCategoria(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getReceitas());
    }
}
