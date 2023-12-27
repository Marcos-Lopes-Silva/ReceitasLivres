package com.receitas.api.api.api.dto;

import com.receitas.api.api.domain.entities.Endereco;

import lombok.Builder;

@Builder
public record DadosDetalhamentoUsuario(Long id, String nome, String login, String senha, Endereco endereco,
        DownloadImageResponse photo) {

}
