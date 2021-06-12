package com.boot.cafemanager.service.table.dto;

import com.boot.cafemanager.service.user.dto.UserDTO;

public class TableDTO {

    private Long id;

    private Long number;

    private UserDTO waiter;

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

    public UserDTO getWaiter() {
        return waiter;
    }

    public void setWaiter(UserDTO waiter) {
        this.waiter = waiter;
    }
}
