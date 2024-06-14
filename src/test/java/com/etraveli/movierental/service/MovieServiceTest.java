package com.etraveli.movierental.service;

import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.service.rent.MovieCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        movieService.movies.clear();
    }

    @Test
    void testRegisterMovie() {
        // Given
        String movieId = "F001";
        Movie movie = new Movie(movieId, "You've Got Mail", MovieCategory.REGULAR);

        //When
        movieService.saveMovie(movie);

        //Then
        assertThat(movieService.movies)
                .containsKey(movieId)
                .containsValue(movie)
                .containsEntry(movieId, movie);
    }

    @Test
    void testGetMovie() {
        // Given
        String movieId = "F009";
        Movie movie = new Movie(movieId, "You've Got Mail", MovieCategory.REGULAR);
        movieService.saveMovie(movie);

        //When
        Movie actual = movieService.getMovieById(movieId);

        //Then
        assertThat(actual)
                .isNotNull()
                .extracting(Movie::getId, Movie::getTitle, Movie::getCode)
                .containsExactly("F009", "You've Got Mail", MovieCategory.REGULAR);
    }

    @Test
    public void testGetMovieByIdWithInvalidId() {
        // Given
        String invalidMovieId = "999";

        // Then
        assertThatThrownBy(() -> {
            this.movieService.getMovieById(invalidMovieId);
        }).isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Movie not found for id:" + invalidMovieId);
    }

}