package com.pismo.accountmanager.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;

import static com.pismo.accountmanager.util.ControllerTools.JsonViews;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionsDto(@JsonView(JsonViews.Select.class) Long transactionId,
                              @JsonView(JsonViews.Create.class) @NotNull Long operationTypeId,
                              @JsonView(JsonViews.Create.class) @NotNull Long accountId,
                              @JsonView(JsonViews.Create.class) @NotNull BigDecimal amount,
                              @JsonView(JsonViews.None.class) LocalDateTime eventDate) implements IdDto{
    @Override
    public Long id() {
        return transactionId;
    }
}