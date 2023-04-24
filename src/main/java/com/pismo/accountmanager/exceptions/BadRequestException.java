package com.pismo.accountmanager.exceptions;

public class BadRequestException  extends RuntimeException {

    public BadRequestException(Class entityClass) { this(entityClass.getSimpleName()); }

    private BadRequestException(String entityName) {
        super("Error when process entity "+ entityName );
    }
}