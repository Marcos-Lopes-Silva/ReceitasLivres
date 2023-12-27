package com.receitas.api.api.api.dto;

import lombok.Builder;

@Builder
public record DownloadImageResponse(Long id, String fileName, String contentType, String contentLength,
        boolean isPublicAccessible, String downloadUrl) {

    public DownloadImageResponse toDomain() {
        return DownloadImageResponse.builder()
                .id(this.id)
                .fileName(this.fileName)
                .contentType(this.contentType)
                .contentLength(this.contentLength)
                .isPublicAccessible(this.isPublicAccessible)
                .downloadUrl(this.downloadUrl)
                .build();
    }
}
