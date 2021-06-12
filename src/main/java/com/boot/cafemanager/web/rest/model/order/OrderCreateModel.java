package com.boot.cafemanager.web.rest.model.order;

import javax.validation.constraints.NotEmpty;

public class OrderCreateModel {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
