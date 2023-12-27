package com.receitas.api.api.domain.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DownloadRequestResult {
    private String downloadSignedUrl;
}
