package com.etraveli.movierental.model;

import com.etraveli.movierental.service.rent.MovieCategory;

import javax.validation.constraints.NotBlank;

public record Movie(
        @NotBlank(message = "id must not be null, empty, or blank")
        String id,
        @NotBlank(message = "title must not be null, empty, or blank")
        String title,
        @NotBlank(message = "code must not be null, empty, or blank")
        MovieCategory code) {
}
