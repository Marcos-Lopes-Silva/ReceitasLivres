package com.receitas.api.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.receitas.api.api.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findById(Long id);

    @Query("SELECT c FROM clientes c WHERE c.id= ?1")
    Cliente getById(Long id);
}
