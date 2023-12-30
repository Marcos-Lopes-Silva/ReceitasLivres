package com.receitas.api.api.domain.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadRequestResult {

    private Long fileReferenceId;
    private String uploadSignedUrl;
    private String contentType;
}
