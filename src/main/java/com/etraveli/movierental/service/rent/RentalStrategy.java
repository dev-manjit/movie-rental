package com.etraveli.movierental.service.rent;

import com.etraveli.movierental.model.MovieRental;

import java.math.BigDecimal;

public interface RentalStrategy {
     BigDecimal calculateMovieRent(MovieRental movieRental);
     int calculateFrequentEnterPoints(MovieRental movieRental);
}
