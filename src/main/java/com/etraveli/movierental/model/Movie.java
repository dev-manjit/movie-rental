package com.etraveli.movierental.model;

import com.etraveli.movierental.service.rent.MovieCategory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Movie {
    @NotNull(message = "id must not be null")
    private String id;
    @NotNull(message = "title must not be null")
    private String title;
    @NotNull(message = "MovieCategory must not be null")
    private MovieCategory code;

    public Movie() {
    }

    public Movie(String id, String title, MovieCategory code) {
        this(title, code);
        this.id = id;
    }

    public Movie(String title, MovieCategory code) {
        this.title = title;
        this.code = code;
    }
}
