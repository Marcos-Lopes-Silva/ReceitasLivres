package com.receitas.api.api.domain.repositories;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.receitas.api.api.domain.entities.FileReference;

public interface FileReferenceRepository extends JpaRepository<FileReference, Long> {
    List<FileReference> findAllByTempIsTrueAndCreatedAtBefore(OffsetDateTime createdAt);
}
