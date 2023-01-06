package com.testreact.reactjs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "cliente_id")
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private Long cpf;

    public User(){

    }

    public User(String nome, String email, String senha, Long cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }




}
