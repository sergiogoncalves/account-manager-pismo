package com.pismo.accountmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ACCOUNTS", schema = "PISMO")
@Getter
@Setter
public class Accounts {

    public Accounts() {
    }

    public Accounts(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "DOCUMENT_NUMBER", nullable = false)
    private String documentNumber;

}


