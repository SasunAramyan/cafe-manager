package com.boot.cafemanager.web.rest.model.product;

import javax.validation.constraints.NotEmpty;

public class ProductCreateModel {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
