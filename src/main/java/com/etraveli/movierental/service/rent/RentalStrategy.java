package com.etraveli.movierental.service.rent;

import com.etraveli.movierental.model.MovieRental;

import java.math.BigDecimal;

public interface RentalStrategy {
    public BigDecimal calculateMovieRent(MovieRental movieRental);
    public int calculateFrequentEnterPoints(MovieRental movieRental);
}
