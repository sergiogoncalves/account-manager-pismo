package com.pismo.accountmanager.exceptions;

public class NotFoundException  extends RuntimeException {

    public NotFoundException(Class entityClass, Long id) { this(entityClass.getSimpleName(), id); }

    private NotFoundException(String entityName, Long id) { super("Couldn't find "+ entityName + " " + id); }
}