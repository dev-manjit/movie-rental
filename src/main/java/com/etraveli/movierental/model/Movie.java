package com.etraveli.movierental.model;

import com.etraveli.movierental.service.rent.MovieCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private String id;
    private String title;
    private MovieCategory code;

    public Movie(String id, String title, MovieCategory code) {
        this(title, code);
        this.id = id;
    }

    public Movie(String title, MovieCategory code) {
        this.title = title;
        this.code = code;
    }
}
