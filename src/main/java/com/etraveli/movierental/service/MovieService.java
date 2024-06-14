package com.etraveli.movierental.service;

import com.etraveli.movierental.model.Movie;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MovieService {
    public static final HashMap<String, Movie> movies = new HashMap();

    public void saveMovie(Movie movie) {
        movies.put(movie.getId(),movie);
    }

    public Movie getMovieById(String id) {
        return Optional.ofNullable(movies.get(id))
                .orElseThrow(() -> new NoSuchElementException("Movie not found for id:"+id));
    };


}
