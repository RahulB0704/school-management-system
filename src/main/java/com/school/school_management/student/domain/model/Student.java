package com.school.school_management.student.domain.model;

import com.school.school_management.Base.AbstractAuditingEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class Student extends AbstractAuditingEntity {

    @Column(nullable = false)
    private String name;
    private String standard;

    private String division;

    private String email;

    private String branch;

    private String phone;

    private String parentContact;

    @ManyToOne(cascade = CascadeType.ALL)
    Address address;

    private Boolean active = true;
}
