package com.receitas.api.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.receitas.api.api.entities.Receitas;

public interface ReceitasRepository extends JpaRepository<Receitas, Long> {

}
