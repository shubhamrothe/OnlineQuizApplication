package com.quizapp.exceptions;


public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
//    private String resourceName;
//    private String fieldName;
//    private Object fieldValue;
//
//    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
//        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
//    }


    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    //for userServiceImpl
    public ResourceNotFoundException(String resource, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resource, fieldName, fieldValue));
    }
}
