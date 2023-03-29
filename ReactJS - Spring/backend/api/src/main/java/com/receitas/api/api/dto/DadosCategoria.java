package com.receitas.api.api.dto;

import java.util.List;

import com.receitas.api.api.entities.Categoria;
import com.receitas.api.api.entities.Receitas;

public record DadosCategoria(Long id, String nome, List<Receitas> receitas) {
    public DadosCategoria(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getReceitas());
    }
}
