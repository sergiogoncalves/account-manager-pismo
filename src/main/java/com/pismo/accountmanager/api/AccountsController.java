package com.pismo.accountmanager.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.pismo.accountmanager.dto.AccountsDto;
import com.pismo.accountmanager.entity.Accounts;
import com.pismo.accountmanager.exceptions.ConflictException;
import com.pismo.accountmanager.exceptions.NotFoundException;
import com.pismo.accountmanager.exceptions.UnprocessableEntityException;
import com.pismo.accountmanager.service.AccountsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.pismo.accountmanager.util.ControllerTools.*;

/**
 *
 * Controller for processing requests for Accounts
 */

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountsController {

    private final AccountsService accountsService;

    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }


    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.Select.class)
    public ResponseEntity<AccountsDto> findAccount(@NotNull @PathVariable("id") Long id) {

        log.debug("Finding account - id {}", id);

        return getResponseEntitySuccess(Optional.ofNullable(accountsService.findAccountById(id)
                .orElseThrow(() -> new NotFoundException(Accounts.class, id))));

    }

    @PostMapping
    @JsonView(JsonViews.Select.class)
    public ResponseEntity<AccountsDto> createAccount(@JsonView(JsonViews.Create.class) @Valid @RequestBody AccountsDto accountsDto, HttpServletRequest request) {

        log.debug("Creating account - accountsDto {}", accountsDto);

        if (accountsService.existsAccount(accountsDto)) throw new ConflictException(Accounts.class);

        return getResponseEntityCreated(Optional.ofNullable(accountsService.createAccount(accountsDto)
                .orElseThrow(() -> new UnprocessableEntityException(Accounts.class))), request.getRequestURI());

    }

}
