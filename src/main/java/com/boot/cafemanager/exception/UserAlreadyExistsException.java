package com.boot.cafemanager.exception;

public class UserAlreadyExistsException extends ValidationException {

    public UserAlreadyExistsException(String fieldName, String filed) {
        super(String.format("User already exists with %s : %s", fieldName, filed));
    }

}
