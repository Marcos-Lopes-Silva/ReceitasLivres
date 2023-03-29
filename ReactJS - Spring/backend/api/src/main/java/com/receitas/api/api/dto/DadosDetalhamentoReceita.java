package com.receitas.api.api.dto;

import com.receitas.api.api.entities.Categoria;
import com.receitas.api.api.entities.Receitas;
import com.receitas.api.api.entities.Usuario;

public record DadosDetalhamentoReceita(Long id, String titulo, String modoPreparo, String ingredientes,
        Usuario usuario,
        String descricao, Categoria categoria, String urlImage, String size, int serve) {
    public DadosDetalhamentoReceita(Receitas dados) {
        this(dados.getId(), dados.getTitulo(), dados.getModoPreparo(), dados.getIngredientes(),
                dados.getUsuario(),
                dados.getDescricao(), dados.getCategoria(), dados.getUrlImage(), dados.getSize(), dados.getServe());
    }
}
