package com.school.school_management.Base;

import com.school.school_management.Base.Exception.ValidationError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class CRUDServiceImpl<DTO extends BaseDTO, ENTITY extends AbstractAuditingEntity>
        implements CRUDService<DTO> {

    protected CRUDRepository<ENTITY> repository;

    protected EntityMapper<DTO, ENTITY> mapper;

    @Override
    @Transactional
    public DTO create(DTO dto) {
        log.debug("Create:" + dto.toString());
        var entity = repository.save(mapper.toEntity(dto));
        repository.flush();
        return mapper.toDto(entity);
    }

    @Override
    public PagedResoure<DTO> findAll(final Pageable page) {
        Page<ENTITY> entities = repository.findAll(page);
        PagedResoure<DTO> DTOPage =
                new PagedResoure<>(
                        mapper.toDto(entities.getContent()),
                        new PagedResoure.PageMetadata(entities.getSize(),
                                entities.getTotalElements(),
                                entities.getTotalPages(),
                                entities.getNumber())
                );
        return DTOPage;
    }

    @Override
    public Optional<DTO> findById(String id) {
        var entities = repository.findById(id).map(mapper::toDto);
        return entities;
    }

    @Override
    @Transactional
    public Optional<DTO> updatePartial(DTO patch) {
        var entity = repository.findById(patch.getId()).get();
        mapper.partialUpdate(entity, patch);
        this.validate(entity);
        repository.save(entity);
        return Optional.of(mapper.toDto(entity));
    }

    @Override
    @Transactional
    public Optional<DTO> delete(String id) {
        Optional<ENTITY> entity =  repository.findById(id);
        if(entity.isPresent()) repository.delete(entity.get());
        return entity.map(mapper::toDto);
    }

    @Override
    public Long count() {
        return repository.count();
    }

    protected void validate(ENTITY dto) throws ValidationError {
        // default it is valid DTO
        return;
    }
}
