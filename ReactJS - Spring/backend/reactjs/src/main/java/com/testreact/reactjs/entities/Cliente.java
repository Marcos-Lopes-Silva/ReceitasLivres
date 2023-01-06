package com.testreact.reactjs.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "cliente")
@Entity 
public class Cliente extends User{
    

    
    @OneToOne(fetch = FetchType.EAGER)
    private Endereco endereco;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Telefone> telefone = new ArrayList<>();
    



    public Cliente(String nome, String email, String senha, Endereco endereco, Long cpf, List<Telefone> telefones){
        super(nome, email, senha, cpf);
        this.telefone = telefones;
        this.endereco = endereco;
        
    }


}
