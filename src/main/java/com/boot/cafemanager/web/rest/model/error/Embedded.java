package com.boot.cafemanager.web.rest.model.error;

import java.util.List;

public class Embedded {

    private List<String> errors;

    private Embedded(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }


    public static Embedded from(List<String> errors) {
        return new Embedded(errors);
    }
}
