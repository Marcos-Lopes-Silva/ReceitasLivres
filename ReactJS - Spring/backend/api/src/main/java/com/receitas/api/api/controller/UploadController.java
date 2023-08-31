package com.receitas.api.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.receitas.api.api.dto.UploadImageRequest;
import com.receitas.api.api.infra.sevices.StorageService;
import com.receitas.api.api.infra.sevices.UploadRequestResult;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RequestMapping("/upload")
@RestController
@AllArgsConstructor
public class UploadController {
    
    private final StorageService storageService;


    @PostMapping("/image")
    public UploadRequestResult newImageUploadRequest(
        @RequestBody @Valid UploadImageRequest request) {
            
        return this.storageService.generatedUploadUrl(request.toDomain());
    }
}
