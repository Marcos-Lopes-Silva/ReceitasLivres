package com.receitaslivres.api.entities;

import com.receitaslivres.api.dto.DadosCadastroReceita;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "receitas")
public class Receitas {

    public Receitas(@Valid DadosCadastroReceita dados) {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "modopreparo")
    private String modoPreparo;
    private String ingredientes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
