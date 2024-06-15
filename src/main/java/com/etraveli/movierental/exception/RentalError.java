package com.etraveli.movierental.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public enum RentalError {

    MOVIE_NOT_FOUND(1001, "Movie not found"),
    RENTAL_DAYS_INVALID(1002, "Invalid rental days, rental days should be more than one day");

    private final int code;
    private final String message;
    private final LocalDateTime timestamp;

    RentalError(int code, String message) {
        this.timestamp = LocalDateTime.now();
        this.code=code;
        this.message = message;
    }
}