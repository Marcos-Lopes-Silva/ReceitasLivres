package com.receitas.api.api.infra.core.properties;

import java.net.URL;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
@ConfigurationProperties("aw.storage")
@Configuration
public class StorageProvider {

    @Valid
    private S3 s3 = new S3();

    @Valid
    private Image image = new Image();

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

    }

    @Data
    public class Image {

        @NotNull
        private URL downloadUrl;
    }

}
