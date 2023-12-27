package com.receitas.api.api.domain.entities;

import java.time.OffsetDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder
@Entity
public class FileReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    private String fileName;
    private String contentType;
    private Long contentLength;

    @Builder.Default
    private boolean temp = true;

    @Enumerated(EnumType.STRING)
    private Type type;

    protected FileReference() {

    }

    public FileReference(Long id, OffsetDateTime createdAt, String name, String contentType, Long contentLength,
            boolean temp, Type type) {

        Objects.requireNonNull(name);
        Objects.requireNonNull(contentType);
        Objects.requireNonNull(contentLength);
        Objects.requireNonNull(type);

        this.id = id;
        this.createdAt = createdAt;
        this.fileName = name;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.temp = temp;
        this.type = type;
    }

    @Getter
    @AllArgsConstructor
    public enum Type {
        IMAGE(true), DOCUMENT(false);

        private final boolean publicAccessible;
    }

    public boolean isPublicAccessible() {
        return this.type.isPublicAccessible();
    }

    public boolean setTemp(boolean temp) {
        return this.temp = temp;
    }

    public String getPath() {
        return this.id + "/" + this.fileName;
    }
}
