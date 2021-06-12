package com.boot.cafemanager.web.rest.model.table;

import javax.validation.constraints.NotNull;

public class TableCreateModel {

    @NotNull
    private Long number;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

}
