package com.boot.cafemanager.web.rest.model.role;

import javax.validation.constraints.NotEmpty;

public class RoleCreateModel {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
