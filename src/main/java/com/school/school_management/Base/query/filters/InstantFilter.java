package com.school.school_management.Base.query.filters;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class InstantFilter implements Serializable {
    public static final long serialVersionUID=1L;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eq;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date not;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gte;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lte;
}
