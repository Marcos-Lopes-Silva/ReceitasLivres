package com.receitaslivres.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.receitaslivres.api.entities.Receitas;

public interface ReceitasRepository extends JpaRepository<Receitas, Long> {

}
