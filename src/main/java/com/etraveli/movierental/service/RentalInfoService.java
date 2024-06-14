package com.etraveli.movierental.service;

import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.service.rent.RentalFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.stream.Collectors.toSet;

@Service
public class RentalInfoService {

    private MovieService movieService;
    private RentalFactory rentalFactory;

    public RentalInfoService(
            MovieService movieService,
            RentalFactory rentalFactory
    ) {
        this.movieService = movieService;
        this.rentalFactory = rentalFactory;
    }

    public String generateStatement(Customer customer) {
        StringBuilder statement = new StringBuilder("Rental Record for " + customer.getName() + "\n");
        BigDecimal totalRent = BigDecimal.ZERO;
        Integer frequentEnterPoints = 0;

        var movieIds = customer.getRentals().stream().map(MovieRental::getMovieId).collect(toSet());
        var movies = movieService.getMovies(movieIds);

        for (MovieRental movieRental : customer.getRentals()) {
            //todo: throw error
            Movie movie = Optional.ofNullable(movies.get(movieRental.getMovieId())).orElseThrow(() -> new RuntimeException("Movie Not Found"));
            var rentalStrategy = rentalFactory.getRentalStrategy(movie.getCode());
            //Calculate and add Movie rent
            BigDecimal movieRent = rentalStrategy.calculateMovieRent(movieRental);
            totalRent = totalRent.add(movieRent);
            //Calculate and add FrequentEnterPoints
            frequentEnterPoints += rentalStrategy.calculateFrequentEnterPoints(movieRental);

            statement.append("\t" + movie.getTitle() + "\t" + movieRent + "\n");
        }
        statement.append("Amount owed is " + totalRent + "\n");
        statement.append("You earned " + frequentEnterPoints + " frequent points\n");
        return statement.toString();
    }

}
