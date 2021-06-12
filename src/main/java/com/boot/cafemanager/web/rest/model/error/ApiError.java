package com.boot.cafemanager.web.rest.model.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiError {

    private String message;
    @JsonProperty("_embedded")
    private Embedded embedded;

    public ApiError(String message) {
        this.message = message;
    }

    public ApiError(String message, Embedded embedded) {
        this.message = message;
        this.embedded = embedded;
    }

    public String getMessage() {
        return message;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }
}
