package com.school.school_management.Base;

import lombok.Data;

import java.time.Instant;

@Data
public class BaseDTO {

    String id;
    String createdBy;
    Instant createdOn;
    String lastModifiedBy;
    Instant lastModifiedOn;
}

