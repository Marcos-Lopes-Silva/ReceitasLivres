package com.testreact.reactjs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.testreact.reactjs.entities.Livros;

public interface LivrosRepository extends CrudRepository<Livros, Long>{
    
    
    List<Livros> findAll();

    <S extends Livros> S save(S c);

    Optional<Livros> findById(Long id);


    void deleteById(Long id);

    
}
