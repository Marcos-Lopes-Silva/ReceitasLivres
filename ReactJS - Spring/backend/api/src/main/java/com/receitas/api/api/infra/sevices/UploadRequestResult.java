package com.receitas.api.api.infra.sevices;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadRequestResult {
    
    private Long fileReferenceId;
    private String uploadSignedUrl; 
}
