package com.pismo.accountmanager.service;

import com.pismo.accountmanager.dto.AccountsDto;
import com.pismo.accountmanager.mapper.MapStructMapper;
import com.pismo.accountmanager.repository.AccountsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *  Service responsible for accounts operations
 */
@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final MapStructMapper mapstructMapper;

    public AccountsService(AccountsRepository accountsRepository, MapStructMapper mapstructMapper) {
        this.accountsRepository = accountsRepository;
        this.mapstructMapper = mapstructMapper;
    }

    public Optional<AccountsDto> findAccountById(Long id) {
        return accountsRepository.findByAccountId(id);
    }

    public Optional<AccountsDto> createAccount(AccountsDto accountsDto) {

        return Optional.ofNullable(mapstructMapper.entityToDTO(accountsRepository.save(mapstructMapper.dtoToEntity(accountsDto))));

    }

    public Boolean existsAccount(AccountsDto accountsDto) {
        return accountsRepository.existsAccountsByDocumentNumber(accountsDto.documentNumber());
    }
}
