package com.pismo.accountmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTIONS", schema = "PISMO")
@Getter
@Setter
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @OneToOne
    @JoinColumn(name = "OPERATION_TYPE_ID", nullable = false)
    private OperationsTypes operationType;

    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Accounts account;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "EVENT_DATE", nullable = false)
    private LocalDateTime eventDate;
}
