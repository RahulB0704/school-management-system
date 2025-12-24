package com.school.school_management.Base.CustomException;

public class ValidationError extends ServiceException{
    public ValidationError(String message) {
        super(message);
    }
}
