package com.receitas.api.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.receitas.api.api.dto.DadosListagemUser;
import com.receitas.api.api.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String subject);

    Page<DadosListagemUser> findAllByAtivoTrue(Pageable pageable);
}
