package com.boot.cafemanager.exception;

public class ProductAlreadyExistsException extends ValidationException {

    public ProductAlreadyExistsException(String fieldName, String filed) {
        super(String.format("Product already exists with %s : %s", fieldName, filed));
    }

}
