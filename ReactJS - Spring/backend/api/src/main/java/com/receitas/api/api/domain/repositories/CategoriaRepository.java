package com.receitas.api.api.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.receitas.api.api.domain.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
