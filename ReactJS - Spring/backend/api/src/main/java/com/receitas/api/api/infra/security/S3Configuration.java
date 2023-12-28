package com.receitas.api.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.receitas.api.api.infra.security.core.properties.StorageProvider;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Configuration {
    

    @Bean
    public S3Presigner presigner(StorageProvider provider) {
        StorageProvider.S3 s3 = provider.getS3();

        AwsBasicCredentials credentials = AwsBasicCredentials.create(s3.getKeyId(), s3.getKeySecret());

        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        return S3Presigner.builder()
                .region(Region.of(s3.getRegion()))
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Bean
    public S3Client s3Client(StorageProvider provider) {

        StorageProvider.S3 s3 = provider.getS3();

        AwsBasicCredentials credentials = AwsBasicCredentials.create(s3.getKeyId(), s3.getKeySecret());

        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        return S3Client.builder()
                .region(Region.of(provider.getS3().getRegion()))
                .credentialsProvider(credentialsProvider)
                .build();
    }
}