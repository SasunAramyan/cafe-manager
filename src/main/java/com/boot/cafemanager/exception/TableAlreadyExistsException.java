package com.boot.cafemanager.exception;

public class TableAlreadyExistsException extends ValidationException {

    public TableAlreadyExistsException(String fieldName, Long field) {
        super(String.format("User already exists with %s : %s", fieldName, field));
    }

}
