package com.receitas.api.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.receitas.api.api.entities.FileReference;

public interface FileReferenceRepository extends JpaRepository<FileReference, Long> {
    
}
