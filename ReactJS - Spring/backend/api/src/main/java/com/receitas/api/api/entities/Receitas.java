package com.receitas.api.api.entities;

import com.receitas.api.api.dto.DadosCadastroReceitas;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "receitas")
@Entity(name = "Receita")
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "modopreparo")
    private String modoPreparo;
    private String ingredientes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Receitas(@Valid DadosCadastroReceitas dados) {
        this.modoPreparo = dados.modoPreparo();
        this.ingredientes = dados.ingredientes();
    }

    @Override
    public String toString() {
        return "Receitas [id=" + id + ", modoPreparo=" + modoPreparo + ", ingredientes=" + ingredientes + ", usuario="
                + usuario.toString() + "]";
    }

}
