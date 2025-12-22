package com.school.school_management.Base.query.filters;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BigDecimalFilter implements Serializable {
    public static final long serialVersionUID = 1L;
    private BigDecimal eq;
    private BigDecimal not;
    private BigDecimal gt;
    private BigDecimal lt;
    private BigDecimal gte;
    private BigDecimal lte;
}
