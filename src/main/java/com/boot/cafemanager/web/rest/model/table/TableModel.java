package com.boot.cafemanager.web.rest.model.table;

import com.boot.cafemanager.web.rest.model.user.UserModel;

public class TableModel {

    private Long id;

    private Long number;

    private UserModel waiter;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public UserModel getWaiter() {
        return waiter;
    }

    public void setWaiter(UserModel waiter) {
        this.waiter = waiter;
    }
}
