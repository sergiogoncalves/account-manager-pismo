package com.pismo.accountmanager.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.pismo.accountmanager.dto.OperationsTypesDto;
import com.pismo.accountmanager.entity.OperationsTypes;
import com.pismo.accountmanager.exceptions.BadRequestException;
import com.pismo.accountmanager.service.OperationsTypesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.pismo.accountmanager.util.ControllerTools.*;

/**
 *
 * Controller for processing requests for OperationsTypes
 */

@RestController
@RequestMapping("/operationsTypes")
@Slf4j
public class OperationsTypesController {

    private final OperationsTypesService operationsTypesService;

    public OperationsTypesController(OperationsTypesService operationsTypesService) {
        this.operationsTypesService = operationsTypesService;
    }

    @GetMapping(path = "")
    @JsonView(JsonViews.Select.class)
    public ResponseEntity<Iterable<OperationsTypesDto>> listOperationsTypes() {

        log.debug("Listing operations types");

        return getResponseEntitySuccess(Optional.ofNullable(operationsTypesService.listOperationsTypes()
                .orElseThrow(() -> new BadRequestException(OperationsTypes.class))));
    }


}
