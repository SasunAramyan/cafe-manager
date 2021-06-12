package com.boot.cafemanager.exception;


public class RoleNotFoundException extends ResourceNotFoundException {

    private static final String CLASS_NAME = "Role";

    public RoleNotFoundException(Long id) {
        super(CLASS_NAME, id);
    }

}
