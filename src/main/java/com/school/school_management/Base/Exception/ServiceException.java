package com.school.school_management.Base.Exception;

public class ServiceException  extends RuntimeException{
    public ServiceException() {
        super("Error");
    }
    public ServiceException(String message) {
        super(message);
    }
}
