package com.boot.cafemanager.exception;

public class RoleAlreadyExistsException extends ValidationException {

    public RoleAlreadyExistsException(String fieldName, String field) {
        super(String.format("User already exists with %s : %s", fieldName, field));
    }

}
