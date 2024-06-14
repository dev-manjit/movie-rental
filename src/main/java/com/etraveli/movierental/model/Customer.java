package com.etraveli.movierental.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Customer {
    private String name;
    private List<MovieRental> rentals;

    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

}
