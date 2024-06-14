package com.etraveli.movierental.service;

import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.service.rent.MovieCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    private final String movieId1 = "F001";
    private final String movieId2 = "F002";
    private final String movieId3 = "F003";
    private final String movieId4 = "F004";
    private final String InvalidMovieId = "F099";

    @BeforeEach
    public void setUp() {
        movieService.movies.clear();
        movieService.movies.put(movieId1, new Movie(movieId1, "You've Got Mail", MovieCategory.REGULAR));
        movieService.movies.put(movieId2, new Movie(movieId2, "Matrix", MovieCategory.REGULAR));
        movieService.movies.put(movieId3, new Movie(movieId3, "Cars", MovieCategory.CHILDREN));
        movieService.movies.put(movieId4, new Movie(movieId4, "Fast & Furious X", MovieCategory.NEW));

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
                .containsEntry(movieId, movie);
    }

    @Test
    void testGetMovies() {
        //Given
        Set<String> movieIds = Set.of(movieId1, movieId2, movieId3, movieId4);

        //When
        Map<String, Movie> movies = movieService.getMovies(movieIds);

        //Then
        assertThat(movies)
                .isNotNull()
                .hasSize(4)
                .containsKeys(movieId1, movieId2, movieId3, movieId4)
                .doesNotContainKeys(InvalidMovieId);
        //And
        assertThat(movies.get(movieId1))
                .isNotNull()
                .extracting(Movie::getId, Movie::getTitle, Movie::getCode)
                .containsExactly("F001", "You've Got Mail", MovieCategory.REGULAR);
        //And
        assertThat(movies.get(movieId2))
                .isNotNull()
                .extracting(Movie::getId, Movie::getTitle, Movie::getCode)
                .containsExactly("F002", "Matrix", MovieCategory.REGULAR);
        //And
        assertThat(movies.get(movieId3))
                .isNotNull()
                .extracting(Movie::getId, Movie::getTitle, Movie::getCode)
                .containsExactly("F003", "Cars", MovieCategory.CHILDREN);
        //And
        assertThat(movies.get(movieId4))
                .isNotNull()
                .extracting(Movie::getId, Movie::getTitle, Movie::getCode)
                .containsExactly("F004", "Fast & Furious X", MovieCategory.NEW);
    }

    @Test
    void testGetMovies_InvalidIdReturnEmptyMap() {
        //Given
        Set<String> movieIds = Set.of(InvalidMovieId);

        //When
        Map<String, Movie> movies = movieService.getMovies(movieIds);

        //Then
        assertThat(movies)
                .isNotNull()
                .hasSize(0);
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
    public void testGetMovie_InvalidId() {
        // Given
        String invalidMovieId = "999";

        // Then
        assertThatThrownBy(() -> {
            this.movieService.getMovieById(invalidMovieId);
        }).isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Movie not found for id:" + invalidMovieId);
    }

}