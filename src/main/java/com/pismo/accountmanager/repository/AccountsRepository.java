package com.pismo.accountmanager.repository;

import com.pismo.accountmanager.dto.AccountsDto;
import com.pismo.accountmanager.entity.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {

    Optional<AccountsDto> findByAccountId(Long accountId);

    boolean existsAccountsByDocumentNumber(String documentNumber);
}
