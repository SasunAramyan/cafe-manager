package com.boot.cafemanager.exception;

public class RoleIsUsedException extends ValidationException {

    public RoleIsUsedException(Long id) {
        super(String.format("Role is used id : %s", id));
    }

}
