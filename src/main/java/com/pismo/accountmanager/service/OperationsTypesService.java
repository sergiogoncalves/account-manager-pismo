package com.pismo.accountmanager.service;

import com.pismo.accountmanager.dto.OperationsTypesDto;
import com.pismo.accountmanager.entity.OperationsTypes;
import com.pismo.accountmanager.mapper.MapStructMapper;
import com.pismo.accountmanager.repository.OperationsTypesRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *  Service responsible for operations types
 */
@Service
public class OperationsTypesService {

    private final OperationsTypesRepository operationsTypesRepository;
    private final MapStructMapper mapStructMapper;

    public OperationsTypesService(OperationsTypesRepository operationsTypesRepository, MapStructMapper mapStructMapper) {
        this.operationsTypesRepository = operationsTypesRepository;
        this.mapStructMapper = mapStructMapper;
    }

    @Cacheable(value = "operationsTypes", unless = "#result.isEmpty()")
    public Optional<Iterable<OperationsTypesDto>> listOperationsTypes() {
        return operationsTypesRepository.findAllByDescriptionIsNotNull();
    }

    public Optional<OperationsTypesDto> findOperationsTypesById(Long id) {
        final Optional<OperationsTypes> operationsTypes = operationsTypesRepository.findById(id);

        if (operationsTypes.isPresent()) {
            return Optional.ofNullable(mapStructMapper.entityToDTO(operationsTypes.get()));
        } else {
            return Optional.empty();
        }
    }
}
