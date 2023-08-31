package com.receitas.api.api.infra.sevices;

import java.net.URL;

import com.receitas.api.api.entities.FileReference;

public interface CloudStorageProvider {
    URL generatedUploadUrl(FileReference fileReference);
}
