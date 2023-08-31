package com.receitas.api.api.dto;

import com.receitas.api.api.entities.FileReference;
import com.receitas.api.api.validations.AllowedContentTypes;
import com.receitas.api.api.validations.AllowedFileExtensions;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record UploadImageRequest(@NotBlank @AllowedFileExtensions({"png", "jpg"}) String fileName, @NotBlank @AllowedContentTypes({"image/png", "image/jpg"}) String contentType, @NotNull @Min(1)Long contentLength) {
    public FileReference toDomain() {
        return FileReference.builder()
        .name(this.fileName)
        .contentType(this.contentType)
        .contentLength(this.contentLength)
        .type(FileReference.Type.IMAGE)
        .build();
    }
}