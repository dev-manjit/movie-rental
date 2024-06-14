package com.etraveli.movierental.model;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class Customer {
    @NotNull(message = "customer name must not be null")
    private String name;
    @NotEmpty(message = "MovieRental list must not be empty")
    @NotNull(message = "MovieRental name must not be null")
    private List<MovieRental> rentals;

    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

}
