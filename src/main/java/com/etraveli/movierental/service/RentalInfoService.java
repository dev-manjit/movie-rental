package com.etraveli.movierental.service;

import com.etraveli.movierental.exception.IllegalOperationException;
import com.etraveli.movierental.exception.RecordNotFoundException;
import com.etraveli.movierental.exception.RentalError;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.service.rent.RentalFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toSet;

@Service
public class RentalInfoService {

    private final MovieService movieService;
    private final RentalFactory rentalFactory;

    public RentalInfoService(MovieService movieService, RentalFactory rentalFactory) {
        this.movieService = movieService;
        this.rentalFactory = rentalFactory;
    }

    public String generateStatement(Customer customer) {

        StringBuilder statement = new StringBuilder("Rental Record for " + customer.name() + "\n");
        BigDecimal totalRent = BigDecimal.ZERO;
        Integer frequentEnterPoints = 0;

        var movieIds = customer.rentals().stream().map(MovieRental::movieId).collect(toSet());
        var movies = movieService.getMovies(movieIds);

        for (MovieRental movieRental : customer.rentals()) {
            if (movieRental.days()<1) {
                throw new IllegalOperationException(RentalError.RENTAL_DAYS_INVALID);
            }
            Movie movie = Optional.ofNullable(movies.get(movieRental.movieId()))
                    .orElseThrow(() -> new RecordNotFoundException(RentalError.MOVIE_NOT_FOUND));

            var rentalStrategy = rentalFactory.getRentalStrategy(movie.code());
            //Calculate and add Movie rent
            BigDecimal movieRent = rentalStrategy.calculateMovieRent(movieRental);
            totalRent = totalRent.add(movieRent);
            //Calculate and add FrequentEnterPoints
            frequentEnterPoints += rentalStrategy.calculateFrequentEnterPoints(movieRental);

            statement.append("\t" + movie.title() + "\t" + movieRent + "\n");
        }
        statement.append("Amount owed is " + totalRent + "\n");
        statement.append("You earned " + frequentEnterPoints + " frequent points\n");
        return statement.toString();
    }

}
