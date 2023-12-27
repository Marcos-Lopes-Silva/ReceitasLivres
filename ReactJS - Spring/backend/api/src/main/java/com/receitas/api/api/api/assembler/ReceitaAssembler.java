package com.receitas.api.api.api.assembler;

import org.springframework.stereotype.Component;

import com.receitas.api.api.api.dto.DadosDetalhamentoReceita;
import com.receitas.api.api.api.dto.DownloadImageResponse;
import com.receitas.api.api.domain.entities.FileReference;
import com.receitas.api.api.domain.entities.Receita;
import com.receitas.api.api.infra.core.properties.StorageProvider;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReceitaAssembler {

    private final StorageProvider storageProvider;

    public DadosDetalhamentoReceita toDTO(Receita receita) {
        return DadosDetalhamentoReceita.builder()
                .id(receita.getId())
                .titulo(receita.getTitulo())
                .descricao(receita.getDescricao())
                .ingredientes(receita.getIngredientes())
                .modoPreparo(receita.getModoPreparo())
                .serve(receita.getServe())
                .size(receita.getSize())
                .categoria(receita.getCategoria())
                .photo(createImage(receita.getPhoto()))
                .usuario(receita.getUsuario())
                .build();
    }

    private DownloadImageResponse createImage(FileReference fileReference) {
        String download = storageProvider.getImage().getDownloadUrl().toString() + "/" + fileReference.getFileName();
        return DownloadImageResponse.builder()
                .id(fileReference.getId())
                .fileName(fileReference.getFileName())
                .downloadUrl(download)
                .build();
    }

}