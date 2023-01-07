package com.receitaslivres.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.receitaslivres.api.entities.TiposUser;

public interface PerfilRepository extends JpaRepository<TiposUser, Long> {

    TiposUser findByNome(String string);

}
