package com.receitas.api.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.receitas.api.api.entities.Receitas;

public interface ReceitaRepository extends JpaRepository<Receitas, Long> {

}
