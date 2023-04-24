package com.pismo.accountmanager.repository;

import com.pismo.accountmanager.dto.OperationsTypesDto;
import com.pismo.accountmanager.entity.OperationsTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationsTypesRepository extends CrudRepository<OperationsTypes, Long> {
    Optional<Iterable<OperationsTypesDto>> findAllByDescriptionIsNotNull();
}
