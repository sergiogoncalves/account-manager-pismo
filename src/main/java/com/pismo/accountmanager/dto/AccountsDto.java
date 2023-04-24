package com.pismo.accountmanager.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import static com.pismo.accountmanager.util.ControllerTools.JsonViews;

public record AccountsDto(@JsonView(JsonViews.Select.class) Long accountId,
                          @JsonView(JsonViews.Create.class) @NotNull @NotEmpty String documentNumber) implements IdDto {
    @Override
    public Long id() {
        return accountId;
    }
}