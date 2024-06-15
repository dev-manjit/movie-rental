package com.etraveli.movierental.controlller;

import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping()
    public ResponseEntity<Movie> registerMovie(@Valid @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.saveMovie(movie));
    }

}