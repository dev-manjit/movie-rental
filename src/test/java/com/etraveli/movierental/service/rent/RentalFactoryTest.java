package com.etraveli.movierental.service.rent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class RentalFactoryTest {

    private RentalFactory rentalFactory;
    private RegularMovieRentalStrategy regularMovieRentalStrategy;
    private NewMovieRentalStrategy newMovieRentalStrategy;
    private ChildMovieRentalStrategy childMovieRentalStrategy;

    @BeforeEach
    void setUp() {
        regularMovieRentalStrategy = mock(RegularMovieRentalStrategy.class);
        newMovieRentalStrategy = mock(NewMovieRentalStrategy.class);
        childMovieRentalStrategy = mock(ChildMovieRentalStrategy.class);

        rentalFactory = new RentalFactory(regularMovieRentalStrategy, newMovieRentalStrategy, childMovieRentalStrategy);
    }

    @Test
    void testGetRentalStrategyForRegular() {
        RentalStrategy strategy = rentalFactory.getRentalStrategy(MovieCategory.REGULAR);
        assertThat(strategy).isEqualTo(regularMovieRentalStrategy);
    }

    @Test
    void testGetRentalStrategyForNew() {
        RentalStrategy strategy = rentalFactory.getRentalStrategy(MovieCategory.NEW);
        assertThat(strategy).isEqualTo(newMovieRentalStrategy);
    }

    @Test
    void testGetRentalStrategyForChildren() {
        RentalStrategy strategy = rentalFactory.getRentalStrategy(MovieCategory.CHILDREN);
        assertThat(strategy).isEqualTo(childMovieRentalStrategy);
    }
}