package com.receitas.api.api.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.receitas.api.api.domain.entities.Receita;

public interface ReceitasRepository extends JpaRepository<Receita, Long> {

    // Page<DadosListagemReceitas> findAll(Pageable paginacao);
}
