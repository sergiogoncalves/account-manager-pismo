package com.pismo.accountmanager.repository;

import com.pismo.accountmanager.entity.Transactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends CrudRepository<Transactions, Long> {}
