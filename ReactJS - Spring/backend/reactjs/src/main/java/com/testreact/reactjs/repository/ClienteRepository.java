package com.testreact.reactjs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.testreact.reactjs.entities.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

    List<Cliente> findAll();

    <S extends Cliente> S save(S c);

    Optional<Cliente> findById(Long id);


    void deleteById(Long id);

    
}
