package com.testreact.reactjs.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "endereco")
@Entity
public class Endereco {

    private String rua;
    @OneToOne(fetch = FetchType.LAZY)
    private Cidade cidade;
    private String cep;

    public Endereco(String rua, String cep, Cidade cidade){
        this.rua = rua;
        this.cep = cep;
        this.cidade = cidade;
    }
}
