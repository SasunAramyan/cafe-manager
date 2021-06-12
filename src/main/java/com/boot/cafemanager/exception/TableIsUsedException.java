package com.boot.cafemanager.exception;

public class TableIsUsedException extends ValidationException {

    public TableIsUsedException(Long id) {
        super(String.format("Role is used id : %s", id));
    }

}
