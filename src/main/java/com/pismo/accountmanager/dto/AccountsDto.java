package com.pismo.accountmanager.dto;

import com.fasterxml.jackson.annotation.JsonView;
import static com.pismo.accountmanager.util.ControllerTools.JsonViews;

public record AccountsDto(@JsonView(JsonViews.Select.class) Long accountId,
                          @JsonView(JsonViews.Create.class) String documentNumber) implements IdDto {
    @Override
    public Long id() {
        return accountId;
    }
}