package com.receitas.api.api.infra.core.exception;

public class StorageCloudException extends RuntimeException {
    public StorageCloudException(String message) {
        super(message);
    }

    public StorageCloudException(String message, Throwable cause) {
        super(message, cause);
    }
}
