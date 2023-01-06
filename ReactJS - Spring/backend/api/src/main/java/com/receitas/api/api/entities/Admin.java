package com.receitas.api.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "admin")
public class Admin extends User {

    public Admin(String nome, String email, String senha, String cpf) {
        super(nome, email, senha, cpf);
    }
}
