package com.receitaslivres.api.entities;

import org.springframework.security.core.GrantedAuthority;

import com.receitaslivres.api.dto.DadosPerfil;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "perfil")
@Entity(name = "TiposUser")
public class TiposUser implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public TiposUser(@Valid DadosPerfil perfil) {
        this.nome = perfil.nome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }

}
