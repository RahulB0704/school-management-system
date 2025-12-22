package com.school.school_management.Base.query.filters;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Filter<T> implements Serializable {
    public static final long serialVersionUID = 1L;

    private T eq;
    private T not;
    private Boolean is;
    private List<T> in;
    private List<T> notIn;
}
