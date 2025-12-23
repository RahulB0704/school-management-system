package com.school.school_management.student.domain.model;

import com.school.school_management.Base.AbstractAuditingEntity;
import jakarta.persistence.Entity;
import jdk.jfr.Enabled;
import lombok.Data;

@Data
@Entity
public class Address extends AbstractAuditingEntity {
    String addressLine;

    String area;

    String city;

    String state;

    String zipcode;

    String country;
}
