package com.pismo.accountmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OPERATIONS_TYPES", schema = "PISMO")
@Getter
@Setter
@Cacheable
public class OperationsTypes {

    public OperationsTypes() {
    }

    public OperationsTypes(Long operationTypeId, String description, SimpleOperationType type) {
        this.operationTypeId = operationTypeId;
        this.description = description;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPERATION_TYPE_ID")
    private Long operationTypeId;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SimpleOperationType type;

}
