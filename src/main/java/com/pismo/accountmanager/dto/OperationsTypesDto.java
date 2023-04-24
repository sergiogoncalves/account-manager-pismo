package com.pismo.accountmanager.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.pismo.accountmanager.entity.SimpleOperationType;
import static com.pismo.accountmanager.util.ControllerTools.JsonViews;

public record OperationsTypesDto(@JsonView(JsonViews.Select.class) Long operationTypeId,
                                 @JsonView(JsonViews.Create.class) String description,
                                 @JsonView(JsonViews.Create.class) SimpleOperationType type) implements IdDto {
    @Override
    public Long id() {
        return operationTypeId;
    }
}
