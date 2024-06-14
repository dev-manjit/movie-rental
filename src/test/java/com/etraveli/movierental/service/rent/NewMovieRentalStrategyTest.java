package com.etraveli.movierental.service.rent;

import com.etraveli.movierental.model.MovieRental;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NewMovieRentalStrategyTest {

    @Autowired
    private NewMovieRentalStrategy newMovieRentalStrategy;

    @Test
    void calculateMovieRent_InitialDays() {
        //Given
        MovieRental movieRental = new MovieRental("999", 4);

        //When
        BigDecimal movieRent = newMovieRentalStrategy.calculateMovieRent(movieRental);

        //Then
        assertThat(movieRent)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(new BigDecimal(12));
    }

    @Test
    void calculateFrequentEnterPoints_InitialDays() {
        //Given
        MovieRental movieRental = new MovieRental("999", 2);

        //When
        int frequentEnterPoints = newMovieRentalStrategy.calculateFrequentEnterPoints(movieRental);

        //Then
        assertThat(frequentEnterPoints)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(0);

    }

    @Test
    void calculateFrequentEnterPoints_ExceedInitialDays() {
        //Given
        MovieRental movieRental = new MovieRental("999", 5);

        //When
        int frequentEnterPoints = newMovieRentalStrategy.calculateFrequentEnterPoints(movieRental);

        //Then
        assertThat(frequentEnterPoints)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(1);
    }


}