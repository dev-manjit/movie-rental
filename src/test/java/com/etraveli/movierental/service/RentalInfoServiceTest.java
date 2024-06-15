package com.etraveli.movierental.service;

import com.etraveli.movierental.exception.IllegalOperationException;
import com.etraveli.movierental.exception.RecordNotFoundException;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.service.rent.MovieCategory;
import com.etraveli.movierental.service.rent.RentalFactory;
import com.etraveli.movierental.service.rent.RentalStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.etraveli.movierental.exception.RentalError.MOVIE_NOT_FOUND;
import static com.etraveli.movierental.exception.RentalError.RENTAL_DAYS_INVALID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalInfoServiceTest {

    @Mock
    private MovieService movieService;

    @Mock
    private RentalFactory rentalFactory;

    @Mock
    private RentalStrategy rentalStrategy;

    @InjectMocks
    private RentalInfoService rentalInfoService;

    @Test
    void testGenerateRentalStatement() {

        // Given
        final String movieId1 = "F001";
        final String movieId2 = "F002";
        final String movieId3 = "F003";
        final String movieId4 = "F004";

        MovieRental movieRental1 = new MovieRental(movieId1, 3);
        MovieRental movieRental2 = new MovieRental(movieId2, 1);
        MovieRental movieRental3 = new MovieRental(movieId3, 5);
        MovieRental movieRental4 = new MovieRental(movieId4, 5);

        Set<String> movieIds = Set.of(movieId1, movieId2, movieId3, movieId4);

        Map<String, Movie> movies = Map.of(
                movieId1, new Movie(movieId1, "You've Got Mail", MovieCategory.REGULAR),
                movieId2, new Movie(movieId2, "Matrix", MovieCategory.REGULAR),
                movieId3, new Movie(movieId3, "Cars", MovieCategory.CHILDREN),
                movieId4, new Movie(movieId4, "Fast & Furious X", MovieCategory.NEW)
        );
        Customer customer = new Customer("Manjiith C.", List.of(movieRental1, movieRental2, movieRental3, movieRental4));

        // When
        when(movieService.getMovies(movieIds)).thenReturn(movies);
        when(rentalFactory.getRentalStrategy(any(MovieCategory.class))).thenReturn(rentalStrategy);
        when(rentalStrategy.calculateMovieRent(any(MovieRental.class))).thenReturn(BigDecimal.TEN);
        when(rentalStrategy.calculateFrequentEnterPoints(any(MovieRental.class))).thenReturn(1);

        String statement = rentalInfoService.generateStatement(customer);

        // Then
        StringBuilder expected = new StringBuilder("Rental Record for " + customer.name() + "\n");
        expected.append("\t" + movies.get(movieId1).title() + "\t" + BigDecimal.TEN + "\n");
        expected.append("\t" + movies.get(movieId2).title() + "\t" + BigDecimal.TEN + "\n");
        expected.append("\t" + movies.get(movieId3).title() + "\t" + BigDecimal.TEN + "\n");
        expected.append("\t" + movies.get(movieId4).title() + "\t" + BigDecimal.TEN + "\n");
        expected.append("Amount owed is 40\n");
        expected.append("You earned 4 frequent points\n");

        assertThat(statement)
                .isNotBlank()
                .isEqualTo(expected.toString());

        // And
        verify(movieService, times(1)).getMovies(movieIds);
        verify(rentalFactory, times(4)).getRentalStrategy(any(MovieCategory.class));
        verify(rentalStrategy, times(4)).calculateMovieRent(any(MovieRental.class));
        verify(rentalStrategy, times(4)).calculateFrequentEnterPoints(any(MovieRental.class));
    }

    @Test
    void generateStatement_InvalidMovieThrowException(){
        final String invalidMovieId = "999";

        MovieRental invalidMovieRental = new MovieRental(invalidMovieId, 1);
        Customer customer = new Customer("Manjiith C.", List.of(invalidMovieRental));

        assertThatThrownBy(() -> {
            this.rentalInfoService.generateStatement(customer);
        }).isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining(MOVIE_NOT_FOUND.getMessage());
    }

    @Test
    void generateStatement_InvalidRentalDaysThrowException(){
        final String movieId1 = "F001";
        final int invalidDay = -1;

        MovieRental invalidMovieRental = new MovieRental(movieId1, invalidDay);
        Customer customer = new Customer("Manjiith C.", List.of(invalidMovieRental));

        assertThatThrownBy(() -> {
            this.rentalInfoService.generateStatement(customer);
        }).isInstanceOf(IllegalOperationException.class)
                .hasMessageContaining(RENTAL_DAYS_INVALID.getMessage());
    }

}