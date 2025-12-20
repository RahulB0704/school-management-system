package com.school.school_management.Base;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CRUDService <DTO>{
    DTO create(DTO u);
    PagedResoure<DTO> findAll(final Pageable page);
    Optional<DTO> findById(String id);
    Optional<DTO> updatePartial(DTO patch);
    Optional<DTO> delete(String id);
    Long count();
}
