package com.receitas.api.api.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.receitas.api.api.api.dto.UploadImageRequest;
import com.receitas.api.api.domain.services.StorageService;
import com.receitas.api.api.domain.services.UploadRequestResult;

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

        return this.storageService.generateUploadUrl(request.toDomain());
    }
}
