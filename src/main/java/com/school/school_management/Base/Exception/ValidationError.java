package com.school.school_management.Base.Exception;

public class ValidationError extends ServiceException{
    public ValidationError(String message) {
        super(message);
    }
}
