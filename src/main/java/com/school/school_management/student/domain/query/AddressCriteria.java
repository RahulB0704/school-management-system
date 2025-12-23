package com.school.school_management.student.domain.query;

import com.school.school_management.Base.query.filters.StringFilter;
import lombok.Data;

@Data
public class AddressCriteria {

    private StringFilter id;

    private String query;

    private StringFilter addressLine;

    private StringFilter area;

    private StringFilter city;

    private StringFilter state;

    private StringFilter zipcode;

    private StringFilter country;
}
