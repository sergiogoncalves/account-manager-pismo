package com.pismo.accountmanager.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException(Class entityClass) { this(entityClass.getSimpleName()); }

    private ConflictException(String entityName) {
        super("Entity " + entityName + " already exists");
    }
}