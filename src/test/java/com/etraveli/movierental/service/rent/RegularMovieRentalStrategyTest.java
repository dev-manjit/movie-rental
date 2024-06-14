package com.etraveli.movierental.service.rent;

import com.etraveli.movierental.model.MovieRental;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RegularMovieRentalStrategyTest {

    @Autowired
    private RegularMovieRentalStrategy regularMovieRentalStrategy;

    @Test
    void calculateMovieRent_InitialDays() {
        //Given
        MovieRental movieRental = new MovieRental("999", 2);

        //When
        BigDecimal movieRent = regularMovieRentalStrategy.calculateMovieRent(movieRental);

        //Then
        assertThat(movieRent)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(new BigDecimal(2));
    }

    @Test
    void calculateMovieRent_ExceedInitialDays() {
        //Given
        MovieRental movieRental = new MovieRental("999", 5);

        //When
        BigDecimal movieRent = regularMovieRentalStrategy.calculateMovieRent(movieRental);

        //Then
        assertThat(movieRent)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(new BigDecimal(6.5));
    }

    @Test
    void calculateFrequentEnterPoints() {
        //Given
        MovieRental movieRental = new MovieRental("999", 2);

        //When
        int frequentEnterPoints = regularMovieRentalStrategy.calculateFrequentEnterPoints(movieRental);

        //Then
        assertThat(frequentEnterPoints)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(0);

    }
}