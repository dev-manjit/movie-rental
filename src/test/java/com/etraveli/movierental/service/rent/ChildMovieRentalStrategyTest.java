package com.etraveli.movierental.service.rent;

import com.etraveli.movierental.model.MovieRental;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ChildMovieRentalStrategyTest {

    @Autowired
    private ChildMovieRentalStrategy childMovieRentalStrategy;

    @Test
    void calculateMovieRent_InitialDays() {
        //Given
        MovieRental movieRental = new MovieRental("999", 2);

        //When
        BigDecimal movieRent = childMovieRentalStrategy.calculateMovieRent(movieRental);

        //Then
        assertThat(movieRent)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(new BigDecimal(1.5));
    }

    @Test
    void calculateMovieRent_ExceedInitialDays() {
        //Given
        MovieRental movieRental = new MovieRental("999", 5);

        //When
        BigDecimal movieRent = childMovieRentalStrategy.calculateMovieRent(movieRental);

        //Then
        assertThat(movieRent)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(new BigDecimal(4.5));
    }

    @Test
    void calculateFrequentEnterPoints() {
        //Given
        MovieRental movieRental = new MovieRental("999", 2);

        //When
        int frequentEnterPoints = childMovieRentalStrategy.calculateFrequentEnterPoints(movieRental);

        //Then
        assertThat(frequentEnterPoints)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(0);

    }

}