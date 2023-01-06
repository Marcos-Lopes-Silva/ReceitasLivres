package com.receitas.api.api.entities;

import com.receitas.api.api.dto.EnderecoDto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {

    private String cidade;
    private String uf;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String cep;

    public Endereco(EnderecoDto dados) {
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.bairro = dados.bairro();
        this.complemento = dados.complemento();
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.cep = dados.cep();
    }

}
