package com.receitas.api.api.domain.services;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.receitas.api.api.domain.entities.FileReference;
import com.receitas.api.api.domain.entities.Usuario;
import com.receitas.api.api.domain.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final StorageService storageService;

    @Transactional
    public Usuario create(Usuario usuario) {
        Objects.requireNonNull(usuario);

        validate(usuario);

        usuario.getPhoto().setTemp(false);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        Objects.requireNonNull(usuario);

        validate(usuario);

        Usuario existUsuario = usuarioRepository.findById(usuario.getId()).orElseThrow(EntityNotFoundException::new);

        if (!usuario.getPhoto().equals(existUsuario.getPhoto())) {
            usuario.getPhoto().setTemp(false);
            this.storageService.softDelete(existUsuario.getPhoto());
        }
        existUsuario.update(usuario);
        usuarioRepository.save(existUsuario);
        usuarioRepository.flush();

        return existUsuario;
    }

    private void validate(Usuario usuario) {

        if (!storageService.fileExists(usuario.getPhoto()))
            throw new RuntimeException(String.format("File %s not found", usuario.getPhoto().getFileName()));

        if (!FileReference.Type.IMAGE.equals(usuario.getPhoto().getType())) {
            throw new RuntimeException(String.format("Arquivo de capa %s deve ser uma imagem",
                    usuario.getPhoto().getId()));
        }
    }
}
