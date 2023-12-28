package com.receitas.api.api.domain.entities;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "receitas")
@Entity(name = "Receita")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(name = "modopreparo")
    private String modoPreparo;
    private String ingredientes;

    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToOne(cascade = CascadeType.ALL)
    private FileReference photo;

    private String size;

    private int serve;

    @Override
    public String toString() {
        return "Receitas [id=" + id + ", modoPreparo=" + modoPreparo + ", ingredientes=" + ingredientes + ", usuario="
                + usuario.toString() + "]";
    }

    public void update(Receita receita) {
        Objects.requireNonNull(receita);

        this.titulo = receita.titulo;
        this.modoPreparo = receita.modoPreparo;
        this.ingredientes = receita.ingredientes;
        this.descricao = receita.descricao;
        this.categoria = receita.categoria;
        this.usuario = receita.usuario;
        this.serve = receita.serve;
        this.photo = receita.photo;
        this.size = receita.size;
    }

}
