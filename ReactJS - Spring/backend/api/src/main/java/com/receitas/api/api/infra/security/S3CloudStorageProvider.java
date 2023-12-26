package com.receitas.api.api.infra.security;

import java.net.URL;
import java.time.Duration;
import org.springframework.stereotype.Component;

import com.receitas.api.api.entities.FileReference;
import com.receitas.api.api.infra.security.core.properties.StorageProvider;
import com.receitas.api.api.infra.sevices.CloudStorageProvider;

import lombok.AllArgsConstructor;
import software.amazon.awssdk.awscore.AwsRequestOverrideConfiguration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Component
@AllArgsConstructor
public class S3CloudStorageProvider implements CloudStorageProvider{

    private final S3Client s3Client;
    
    private final S3Presigner s3Presigner;

    private final StorageProvider provider;

    @Override
    public URL generatedUploadUrl(FileReference fileReference) {

        var builder = AwsRequestOverrideConfiguration.builder();

        // if(fileReference.isPublicAccessible()) {
            builder.putRawQueryParameter("x-amz-acl", "public-read");
        // }

        System.out.println(fileReference.getContentType());

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(provider.getS3().getBucket())
        .key(fileReference.getPath())
        .contentLength(fileReference.getContentLength())
        .contentType(fileReference.getContentType())
        .contentLength(fileReference.getContentLength())
        .acl(fileReference.isPublicAccessible() ? "public-read" : "public-read")
        .overrideConfiguration(builder.build())
        .build();
        
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
        .signatureDuration(Duration.ofMinutes(30))
        .putObjectRequest(putObjectRequest)
        .build();
        System.out.println(s3Presigner.presignPutObject(presignRequest).signedPayload());
        System.out.println(s3Presigner.presignPutObject(presignRequest).signedHeaders());
        return s3Presigner.presignPutObject(presignRequest).url();
    }

    
}
