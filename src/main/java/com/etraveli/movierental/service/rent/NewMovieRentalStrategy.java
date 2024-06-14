package com.etraveli.movierental.service.rent;

import com.etraveli.movierental.model.MovieRental;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class NewMovieRentalStrategy implements RentalStrategy {

    private static final int INITIAL_DAYS = 2;
    private static final BigDecimal NORMAL_RENT = new BigDecimal(3);


    /**
     * Calculates the rental amount for a movie based on the number of rental days.
     *
     * @param movieRental the MovieRental object containing rental details
     * @return the calculated rental amount
     */
    @Override
    public BigDecimal calculateMovieRent(MovieRental movieRental) {
        return NORMAL_RENT.multiply(new BigDecimal(movieRental.getDays()));
    }

    /**
     * Calculates the Frequent Enter Points for a movie rental.
     * One FrequentEnterPoints if exceed INITIAL_DAYS for New Category Movies
     *
     * @param movieRental the MovieRental object containing rental details
     * @return the calculated frequent renter points
     */
    @Override
    public int calculateFrequentEnterPoints(MovieRental movieRental) {
        return movieRental.getDays() > INITIAL_DAYS ? 1 : 0;
    }
}
