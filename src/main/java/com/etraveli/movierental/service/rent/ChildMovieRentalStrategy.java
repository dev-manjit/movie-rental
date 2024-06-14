package com.etraveli.movierental.service.rent;

import com.etraveli.movierental.model.MovieRental;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class ChildMovieRentalStrategy implements RentalStrategy {

    private static final BigDecimal INITIAL_AMOUNT = new BigDecimal(1.5);
    private static final int INITIAL_DAYS = 3;
    private static final BigDecimal NORMAL_RENT = new BigDecimal(1.5);

    /**
     * Calculates the rental amount for a movie based on the number of rental days.
     * Which is exceeded INITIAL_DAYS
     *
     * @param movieRental the MovieRental object containing rental details
     * @return the calculated rental amount
     */
    @Override
    public BigDecimal calculateMovieRent(MovieRental movieRental) {

        if (movieRental.getDays() > INITIAL_DAYS) {
            var exceedDays = new BigDecimal(movieRental.getDays() - INITIAL_DAYS);
            return INITIAL_AMOUNT.add(NORMAL_RENT.multiply(exceedDays));
        }
        return INITIAL_AMOUNT;

    }

    /**
     * Calculates the Frequent Enter Points for a movie rental.
     * No FrequentEnterPoints for Child Movies
     *
     * @param movieRental the MovieRental object containing rental details
     * @return the calculated frequent renter points (always returns 0 in this implementation)
     */
    @Override
    public int calculateFrequentEnterPoints(MovieRental movieRental) {
        return 0;
    }
}
