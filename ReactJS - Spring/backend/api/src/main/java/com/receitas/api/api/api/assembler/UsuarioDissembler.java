package com.receitas.api.api.api.assembler;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.receitas.api.api.api.dto.DadosCadastroUsuario;
import com.receitas.api.api.api.dto.DadosEditarUsuario;
import com.receitas.api.api.domain.entities.Endereco;
import com.receitas.api.api.domain.entities.FileReference;
import com.receitas.api.api.domain.entities.Usuario;
import com.receitas.api.api.domain.repositories.FileReferenceRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UsuarioDissembler {

    private final FileReferenceRepository fileReferenceRepository;
    private final PasswordEncoder encoder;

    public Usuario toDomain(DadosCadastroUsuario dados) {
        return Usuario.builder()
                .nome(dados.nome())
                .login(dados.login())
                .senha(encoder.encode(dados.senha()))
                .ativo(true)
                .endereco(new Endereco(dados.endereco()))
                .receitas(null)
                .photo(dados.photoId() != null ? findFileReferenceById(dados.photoId()) : null)
                .build();
    }

    public Usuario toDomain(DadosEditarUsuario dados, Long id) {
        return Usuario.builder()
                .nome(dados.nome())
                .id(id)
                .endereco(new Endereco(dados.endereco()))
                .photo(dados.photoId() != null ? findFileReferenceById(dados.photoId()) : null)
                .build();
    }

    private FileReference findFileReferenceById(Long id) {
        return fileReferenceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
