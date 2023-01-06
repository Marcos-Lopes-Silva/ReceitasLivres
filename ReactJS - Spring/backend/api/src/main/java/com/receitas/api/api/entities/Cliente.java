package com.receitas.api.api.entities;

import java.util.ArrayList;
import java.util.List;

import com.receitas.api.api.dto.ClienteDto;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "clientes")
@Entity
public class Cliente extends User {

    @Embedded
    private Endereco endereco;

    @Embedded
    private List<Telefone> telefone = new ArrayList<>();
    @OneToMany(mappedBy = "cliente")
    @Column(name = "receitas_id")
    private Receitas receitas;

    public Cliente(ClienteDto dados) {
        super(dados.nome(), dados.email(), dados.senha(), dados.cpf());
        this.endereco = new Endereco(dados.endereco());

    }

}
