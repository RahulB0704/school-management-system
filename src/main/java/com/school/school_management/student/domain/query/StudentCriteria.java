package com.school.school_management.student.domain.query;

import lombok.Data;

@Data
public class StudentCriteria {
    private String query;

    private String name;
    private String standard;
    private String division;
    private Boolean active;
}
