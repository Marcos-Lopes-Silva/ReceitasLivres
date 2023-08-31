package com.receitas.api.api.infra.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Validated
@ConfigurationProperties("aw.storage")
@Configuration
public class StorageProvider {


    @Valid
    private S3 s3 = new S3();
    
    @Data
    public class S3 {

        @NotBlank
        private String keyId;
        @NotBlank
        private String keySecret;
        @NotBlank
        private String bucket;
        @NotBlank
        private String region;
        @NotBlank
        private String signatureVersion;

    }

}   
