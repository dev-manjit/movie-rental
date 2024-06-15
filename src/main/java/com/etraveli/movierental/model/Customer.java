package com.etraveli.movierental.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public record Customer(
        @NotBlank(message = "customer name  must not be null, empty, or blank") String name,
        @NotEmpty(message = "MovieRental list must not be null, empty") List<MovieRental> rentals
) {
}
