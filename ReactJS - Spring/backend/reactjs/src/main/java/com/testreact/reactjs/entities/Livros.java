package com.testreact.reactjs.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity @Table(name="livros")
public class Livros {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "livros_id")
    private Long id;

    private String nome;

    private String editora;

    private String descricao;

    private double preco;

    private int quantidade;

    
    public Livros(String nome, String editora, String descricao, double preco, int quantidade) {
        this.nome = nome;
        this.editora = editora;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }


    public Livros(){

    }

}
