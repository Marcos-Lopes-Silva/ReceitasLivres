package com.receitas.api.api.api.assembler;

import org.springframework.stereotype.Component;

import com.receitas.api.api.api.dto.DadosCadastroReceitas;
import com.receitas.api.api.domain.entities.Categoria;
import com.receitas.api.api.domain.entities.FileReference;
import com.receitas.api.api.domain.entities.Receita;
import com.receitas.api.api.domain.entities.Usuario;
import com.receitas.api.api.domain.repositories.CategoriaRepository;
import com.receitas.api.api.domain.repositories.FileReferenceRepository;
import com.receitas.api.api.domain.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReceitaDissembler {

    private final FileReferenceRepository fileReferenceRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public Receita toDomain(DadosCadastroReceitas dados) {

        return Receita.builder()
                .titulo(dados.titulo())
                .descricao(dados.descricao())
                .ingredientes(dados.ingredientes())
                .modoPreparo(dados.modoPreparo())
                .serve(dados.serve())
                .size(dados.size())
                .categoria(findCategoriaById(dados.categoriaId()))
                .photo(findFileReferenceById(dados.photoId()))
                .usuario(findUsuarioById(dados.usuario().getId()))
                .build();
    }

    public Receita toDomain(DadosCadastroReceitas dados, Long id) {

        return Receita.builder()
                .id(id)
                .titulo(dados.titulo())
                .descricao(dados.descricao())
                .ingredientes(dados.ingredientes())
                .modoPreparo(dados.modoPreparo())
                .serve(dados.serve())
                .size(dados.size())
                .categoria(findCategoriaById(dados.categoriaId()))
                .photo(findFileReferenceById(dados.photoId()))
                .usuario(findUsuarioById(dados.usuario().getId()))
                .build();
    }

    private Categoria findCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    private Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    private FileReference findFileReferenceById(Long id) {
        return fileReferenceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
}
