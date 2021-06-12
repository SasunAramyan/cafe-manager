package com.boot.cafemanager.exception;

public class TableAlreadyIsUsedException extends ValidationException {

    public TableAlreadyIsUsedException(Long id) {
        super(String.format("Table is used id : %s", id));
    }

}
