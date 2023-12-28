package com.receitas.api.api.api.assembler;

import org.springframework.stereotype.Component;

import com.receitas.api.api.api.dto.DadosDetalhamentoUsuario;
import com.receitas.api.api.api.dto.DownloadImageResponse;
import com.receitas.api.api.domain.entities.FileReference;
import com.receitas.api.api.domain.entities.Usuario;
import com.receitas.api.api.infra.core.properties.StorageProvider;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UsuarioAssembler {

    private final StorageProvider storageProvider;

    public DadosDetalhamentoUsuario toDTO(Usuario usuario) {
        return DadosDetalhamentoUsuario.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .login(usuario.getLogin())
                .endereco(usuario.getEndereco())
                .photo(usuario.getPhoto() != null ? createImage(usuario.getPhoto()) : null)
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
