package com.receitas.api.api.entities;

import java.util.Optional;

import com.receitas.api.api.dto.ReceitaDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "receitas")
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String tituloDaReceita;
    public String modoDePreparo;
    public String ingredientes;
    @ManyToOne
    @Column(name = "cliente_id")
    public Cliente cliente;

    public Receitas(ReceitaDto dados, Optional<Cliente> cliente) {
        this.tituloDaReceita = dados.titulo();
        this.modoDePreparo = dados.modoDePreparo();
        this.ingredientes = dados.ingredientes().toString().replaceAll("[]", "");
        this.cliente = cliente.get();
    }
}
