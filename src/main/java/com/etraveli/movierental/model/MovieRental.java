package com.etraveli.movierental.model;

import lombok.Getter;

@Getter
public class MovieRental {
    private String movieId;
    private int days;

    public MovieRental(String movieId, int days) {
        this.movieId = movieId;
        this.days = days;
    }
}
