package com.testreact.reactjs.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Getter
@Table(name = "admin")
public class Admin extends User{
    
    public Admin(String nome, String email, String senha, Long cpf){
        super(nome, email, senha, cpf);
    }
}
