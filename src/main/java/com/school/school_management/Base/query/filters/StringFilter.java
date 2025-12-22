package com.school.school_management.Base.query.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringFilter extends Filter<String>{
    public static final long serialVersionUID = 1L;

    private String contains;
    private String doesNotContain;
}
