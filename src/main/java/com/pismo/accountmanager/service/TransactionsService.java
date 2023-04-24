package com.pismo.accountmanager.service;

import com.pismo.accountmanager.dto.AccountsDto;
import com.pismo.accountmanager.dto.OperationsTypesDto;
import com.pismo.accountmanager.dto.TransactionsDto;
import com.pismo.accountmanager.entity.Transactions;
import com.pismo.accountmanager.mapper.MapStructMapper;
import com.pismo.accountmanager.repository.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


/**
 *  Service responsible for transaction operations
 */
@Service
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final MapStructMapper mapstructMapper;

    public TransactionsService(TransactionsRepository transactionsRepository, MapStructMapper mapstructMapper) {
        this.transactionsRepository = transactionsRepository;
        this.mapstructMapper = mapstructMapper;
    }

    public Optional<TransactionsDto> findTransactionById(Long id) {
        final Optional<Transactions> transaction = transactionsRepository.findById(id);

        if (transaction.isPresent()) {
            return Optional.ofNullable(mapstructMapper.entityToDTO(transaction.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<TransactionsDto> createTransaction(TransactionsDto transactionsDto, AccountsDto accountsDto, OperationsTypesDto operationsTypesDto) {
        final Transactions transaction = mapstructMapper.dtoToEntity(transactionsDto);
        transaction.setAccount(mapstructMapper.dtoToEntity(accountsDto));
        transaction.setOperationType(mapstructMapper.dtoToEntity(operationsTypesDto));
        transaction.setEventDate(LocalDateTime.now());
        return Optional.ofNullable(mapstructMapper.entityToDTO(transactionsRepository.save(transaction)));
    }
}
