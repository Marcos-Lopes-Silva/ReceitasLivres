package com.testreact.reactjs.dto;

import java.util.List;

import com.testreact.reactjs.entities.Endereco;
import com.testreact.reactjs.entities.Telefone;

public record ClienteDto(@NotBlank String nome, @NotBlank @Email String email, String senha, Endereco endereco,
        Long cpf,
        List<Telefone> telefone) {

}
