package com.etraveli.movierental.service;

import com.etraveli.movierental.exception.RecordNotFoundException;
import com.etraveli.movierental.exception.RentalError;
import com.etraveli.movierental.model.Movie;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {
    /**
     * "this will be changed to DB feature implementation"
     */
    public static final HashMap<String, Movie> movies = new HashMap();

    /**
     * Saves a movie to the in-memory map of movies.
     * <p>
     * This method adds or updates the given movie in the in-memory map using the movie's ID as the key.
     * Note: This method should be updated to persist the movie to a database.
     *
     * @param movie the movie to be saved
     * @return the {@link Movie} object associated with the given ID
     */
    public Movie saveMovie(Movie movie) {
        movies.put(movie.id(), movie);
        return movie;
    }

    /**
     * Retrieves a movie by its ID from the in-memory map of movies.
     * <p>
     * @param id the ID of the movie to be retrieved
     * @return the {@link Movie} object associated with the given ID
     * @throws NoSuchElementException if no movie is found for the given ID
     */
    public Movie getMovieById(String id) {
        return Optional.ofNullable(movies.get(id))
                .orElseThrow(() -> new RecordNotFoundException(RentalError.MOVIE_NOT_FOUND));
    }

    /**
     * Retrieves a map of movies based on the provided set of movie IDs.
     * <p>
     * This method filters the existing collection of movies and returns only those
     * that match the given set of movie IDs. It avoids multiple database hits by
     * leveraging the in-memory map of movies, ensuring efficient retrieval.
     *
     * @param movieIds a set of movie IDs to retrieve
     * @return a map of movie IDs to Movie objects that match the given set of IDs
     */
    public Map<String, Movie> getMovies(Set<String> movieIds) {
        return movies.entrySet().stream()
                .filter(movie -> movieIds.contains(movie.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
