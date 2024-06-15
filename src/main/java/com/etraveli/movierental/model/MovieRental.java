package com.etraveli.movierental.model;


import javax.validation.constraints.NotNull;

public record MovieRental(
        @NotNull(message = "movieId must not be null")
        String movieId,
        @NotNull(message = "rental days must not be null")
        Integer days
) {
}

