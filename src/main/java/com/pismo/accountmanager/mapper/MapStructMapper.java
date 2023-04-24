package com.pismo.accountmanager.mapper;

import com.pismo.accountmanager.dto.AccountsDto;
import com.pismo.accountmanager.dto.OperationsTypesDto;
import com.pismo.accountmanager.dto.TransactionsDto;
import com.pismo.accountmanager.entity.Accounts;
import com.pismo.accountmanager.entity.OperationsTypes;
import com.pismo.accountmanager.entity.Transactions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 *  Maps the entities to transform into dtos
 */
@Mapper(componentModel = "spring")
public interface MapStructMapper {

    OperationsTypesDto entityToDTO(OperationsTypes operationsTypes);

    OperationsTypes dtoToEntity(OperationsTypesDto operationsTypesDto);

    AccountsDto entityToDTO(Accounts accounts);

    Accounts dtoToEntity(AccountsDto accountsDto);

    @Mapping(source = "transactions", target = "accountId", qualifiedByName = "accountId")
    @Mapping(source = "transactions", target = "operationTypeId", qualifiedByName = "operationTypeId")
    TransactionsDto entityToDTO(Transactions transactions);

    @Named("accountId")
    default Long transactionsToAccountId(Transactions transactions) {
        return transactions.getAccount().getAccountId();
    }

    @Named("operationTypeId")
    default Long transactionsToOperationTypeId(Transactions transactions) {
        return transactions.getOperationType().getOperationTypeId();
    }

    Transactions dtoToEntity(TransactionsDto transactionsDto);

}
