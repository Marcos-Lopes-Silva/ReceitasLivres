package com.receitas.api.api.domain.services;

import java.net.URL;

import com.receitas.api.api.domain.entities.FileReference;

public interface CloudStorageProvider {
    URL generatedUploadUrl(FileReference fileReference);

    URL generatedDownloadUrl(FileReference fileReference);

    boolean fileExists(String filePath);

    void moveFile(String fromPath, String toPath);

    void removeFile(String filePath);
}
