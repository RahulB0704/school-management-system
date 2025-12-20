package com.school.school_management.student.domain.model;

import com.school.school_management.Base.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class Student extends AbstractAuditingEntity {

    @Column(nullable = false)
    private String name;
    private String standard;

    private String division;

    private String parentContact;

    private Boolean active = true;
}
