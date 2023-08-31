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
import jakarta.persistence.OneToOne;
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

    public Receitas(@Valid DadosCadastroReceitas dados) {
        this.titulo = dados.titulo();
        this.modoPreparo = dados.modoPreparo();
        this.ingredientes = dados.ingredientes();
        this.descricao = dados.descricao();
        this.categoria = dados.categoria();
        this.usuario = dados.usuario();
        this.serve = dados.serve();
        this.photo = dados.photo();
        this.size = dados.size();
    }

    @Override
    public String toString() {
        return "Receitas [id=" + id + ", modoPreparo=" + modoPreparo + ", ingredientes=" + ingredientes + ", usuario="
                + usuario.toString() + "]";
    }

}
