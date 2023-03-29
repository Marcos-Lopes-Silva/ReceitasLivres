package com.receitas.api.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.receitas.api.api.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
