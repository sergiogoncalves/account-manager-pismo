package com.pismo.accountmanager.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.pismo.accountmanager.dto.AccountsDto;
import com.pismo.accountmanager.dto.OperationsTypesDto;
import com.pismo.accountmanager.dto.TransactionsDto;
import com.pismo.accountmanager.entity.Accounts;
import com.pismo.accountmanager.entity.OperationsTypes;
import com.pismo.accountmanager.entity.Transactions;
import com.pismo.accountmanager.exceptions.NotFoundException;
import com.pismo.accountmanager.exceptions.UnprocessableEntityException;
import com.pismo.accountmanager.service.AccountsService;
import com.pismo.accountmanager.service.OperationsTypesService;
import com.pismo.accountmanager.service.TransactionsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.pismo.accountmanager.util.ControllerTools.*;

/**
 * Controller for processing requests for Transactions
 */

@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionsController {

    private final TransactionsService transactionsService;
    private final AccountsService accountsService;
    private final OperationsTypesService operationsTypesService;

    public TransactionsController(TransactionsService transactionsService, AccountsService accountsService, OperationsTypesService operationsTypesService) {
        this.transactionsService = transactionsService;
        this.accountsService = accountsService;
        this.operationsTypesService = operationsTypesService;
    }


    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.Select.class)
    public ResponseEntity<TransactionsDto> findTransaction(@NotNull @PathVariable("id") Long id) {

        log.debug("Finding transaction - id {}", id);

        return getResponseEntitySuccess(Optional.ofNullable(transactionsService.findTransactionById(id)
                .orElseThrow(() -> new NotFoundException(Transactions.class, id))));
    }

    @PostMapping
    @JsonView(JsonViews.Select.class)
    public ResponseEntity<TransactionsDto> createTransaction(@JsonView(JsonViews.Create.class) @NotNull @RequestBody TransactionsDto transactionsDto, HttpServletRequest request) {

        log.debug("Creating transaction - transactionsDto {}", transactionsDto);

        final AccountsDto accountsDto = accountsService.findAccountById(transactionsDto.accountId())
                .orElseThrow(() -> new NotFoundException(Accounts.class, transactionsDto.accountId()));

        final OperationsTypesDto operationsTypesDto = operationsTypesService.findOperationsTypesById(transactionsDto.operationTypeId())
                .orElseThrow(() -> new NotFoundException(OperationsTypes.class, transactionsDto.operationTypeId()));

        return getResponseEntityCreated(Optional.ofNullable(transactionsService.createTransaction(transactionsDto, accountsDto, operationsTypesDto)
                .orElseThrow(() -> new UnprocessableEntityException(Transactions.class))), request.getRequestURI());

    }

}
