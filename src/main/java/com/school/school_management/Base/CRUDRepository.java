package com.school.school_management.Base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CRUDRepository<E extends AbstractAuditingEntity>
        extends JpaRepository<E, String>, JpaSpecificationExecutor<E> {

}
