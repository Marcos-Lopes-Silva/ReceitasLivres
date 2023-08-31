package com.receitas.api.api.infra.sevices;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.receitas.api.api.entities.FileReference;
import com.receitas.api.api.infra.security.S3CloudStorageProvider;
import com.receitas.api.api.repositories.FileReferenceRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class StorageService {
    

    private final S3CloudStorageProvider cloudStorageProvider;
    
    private final FileReferenceRepository repository;

    public UploadRequestResult generatedUploadUrl(FileReference fileReference) {
        Objects.requireNonNull(fileReference);
        repository.save(fileReference);

        var uploadUrl = cloudStorageProvider.generatedUploadUrl(fileReference);

        return new UploadRequestResult(fileReference.getId(), uploadUrl.toString());
    }
}
