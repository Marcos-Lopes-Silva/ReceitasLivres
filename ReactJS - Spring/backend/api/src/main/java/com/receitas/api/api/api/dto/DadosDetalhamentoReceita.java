package com.receitas.api.api.api.dto;

import com.receitas.api.api.domain.entities.Categoria;
import com.receitas.api.api.domain.entities.Usuario;

import lombok.Builder;

@Builder
public record DadosDetalhamentoReceita(Long id, String titulo, String modoPreparo, String ingredientes,
        Usuario usuario,
        String descricao, Categoria categoria, DownloadImageResponse photo, String size, int serve) {

    // public DadosDetalhamentoReceita toDomain() {
    // return
    // DadosDetalhamentoReceita.builder().id(this.id).titulo(this.titulo).modoPreparo(this.modoPreparo)
    // .ingredientes(this.ingredientes).usuario(this.usuario).descricao(this.descricao)
    // .categoria(this.categoria).photo(this.photo).size(this.size).serve(this.serve).build();
    // }
}
