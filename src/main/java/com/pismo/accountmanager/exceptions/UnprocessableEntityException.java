package com.pismo.accountmanager.exceptions;

public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException(Class entityClass) { this(entityClass.getSimpleName()); }

    private UnprocessableEntityException(String entityName) { super("Error when process entity " + entityName); }
}