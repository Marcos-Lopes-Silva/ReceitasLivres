package com.receitaslivres.api.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.receitaslivres.api.dto.DadosAtualizacaoUser;
import com.receitaslivres.api.dto.DadosCadastroUserTipo;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_tipo", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<TiposUser> tipo = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Receitas> userReceitas = new ArrayList<>();

    public User(@Valid DadosCadastroUserTipo user) {
        this.nome = user.nome();
        this.email = user.email();
        this.senha = user.senha();
        this.endereco = new Endereco(user.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoUser dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void adicionarPerfil(TiposUser novo) {
        this.tipo.add(novo);
    }

    public void excluir() {
        this.ativo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.tipo;
    }

    @Override
    public String getPassword() {

        return senha;
    }

    @Override
    public String getUsername() {

        return email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
