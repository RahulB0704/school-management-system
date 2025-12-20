package com.school.school_management.Base;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(
            name="system-uuid",
            strategy = "uuid"
    )
    @Column(
            name="id",
            columnDefinition = "VARCHAR(255)",
            insertable = false,
            updatable = false,
            nullable = false
    )
    private String id;

    @CreatedBy
    @Column(name = "created_by", length = 50, updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_on", updatable = false)
    private Instant createdOn = Instant.now();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_on")
    private Instant lastModifiedOn = Instant.now();
}
