package com.testreact.reactjs.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vendedor {
    

    private Endereco endereco;
    
}
