package com.etraveli.movierental.service.rent;

import org.springframework.stereotype.Component;

@Component
public class RentalFactory {

    private RegularMovieRentalStrategy regularMovieRentalStrategy;
    private NewMovieRentalStrategy newMovieRentalStrategy;
    private ChildMovieRentalStrategy childMovieRentalStrategy;

    public RentalFactory(
            RegularMovieRentalStrategy regularMovieRentalStrategy,
            NewMovieRentalStrategy newMovieRentalStrategy,
            ChildMovieRentalStrategy childMovieRentalStrategy
    ) {
        this.regularMovieRentalStrategy = regularMovieRentalStrategy;
        this.newMovieRentalStrategy = newMovieRentalStrategy;
        this.childMovieRentalStrategy = childMovieRentalStrategy;
    }
    /**
     * Returns the appropriate rental strategy based on the movie category.
     *
     * @param category the category of the movie
     * @return the rental strategy for the specified movie category
     */
    public RentalStrategy getRentalStrategy(MovieCategory category) {
        return switch (category) {
            case REGULAR -> regularMovieRentalStrategy;
            case NEW -> newMovieRentalStrategy;
            case CHILDREN -> childMovieRentalStrategy;
        };
    }

}
