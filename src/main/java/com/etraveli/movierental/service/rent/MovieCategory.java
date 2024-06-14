package com.etraveli.movierental.service.rent;

import lombok.Getter;

@Getter
public enum MovieCategory {
    REGULAR("Regular"),
    NEW("New"),
    CHILDREN("Children"),
    DEFAULT("Default");

    private final String code;

    MovieCategory(String code) {
        this.code = code;
    }
}
