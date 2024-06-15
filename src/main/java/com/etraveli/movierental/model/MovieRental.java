package com.etraveli.movierental.model;

import lombok.Getter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class MovieRental {
    @NotNull(message = "movieId must not be null")
    private String movieId;
    @NotNull(message = "rental days must not be null")
    private Integer days;

    public MovieRental(String movieId, Integer days) {
        this.movieId = movieId;
        this.days = days;
    }
}
