package com.etraveli.movierental.controlller;

import com.etraveli.movierental.exception.IllegalOperationException;
import com.etraveli.movierental.exception.RecordNotFoundException;
import com.etraveli.movierental.exception.RentalError;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.service.RentalInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.etraveli.movierental.exception.RentalError.MOVIE_NOT_FOUND;
import static com.etraveli.movierental.exception.RentalError.RENTAL_DAYS_INVALID;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentalInfoController.class)
public class RentalInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalInfoService rentalInfoService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGenerateStatement_ShouldReturnStatement() throws Exception {
        // Given
        MovieRental sampleRental = new MovieRental("movie1", 5);
        Customer sampleCustomer = new Customer("Manjiith C.", List.of(sampleRental));
        String expectedStatement = "Rental Statement";

        // When
        when(rentalInfoService.generateStatement(any(Customer.class))).thenReturn(expectedStatement);

        // Convert the sample customer to JSON
        String customerJson = objectMapper.writeValueAsString(sampleCustomer);

        //Then
        mockMvc.perform(get("/rentals/statement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedStatement));
        verify(rentalInfoService, times(1)).generateStatement(any(Customer.class));
    }

    @Test
    public void generateStatement_InvalidMovieThrowException() throws Exception {

        // Given
        MovieRental sampleRental = new MovieRental("InvalidMovieId", 5);
        Customer sampleCustomer = new Customer("Manjiith C.", List.of(sampleRental));

        // When
        when(rentalInfoService.generateStatement(any(Customer.class))).thenThrow(new RecordNotFoundException(MOVIE_NOT_FOUND));
        // Convert the sample customer to JSON
        String customerJson = objectMapper.writeValueAsString(sampleCustomer);

        //Then
        mockMvc.perform(get("/rentals/statement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is(MOVIE_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.message", is(MOVIE_NOT_FOUND.getMessage())));
    }

    @Test
    public void generateStatement_InvalidRentalDateThrowException() throws Exception {

        // Given
        MovieRental sampleRental = new MovieRental("F001", 0);
        Customer sampleCustomer = new Customer("Manjiith C.", List.of(sampleRental));

        // When
        when(rentalInfoService.generateStatement(any(Customer.class))).thenThrow(new IllegalOperationException(RENTAL_DAYS_INVALID));
        // Convert the sample customer to JSON
        String customerJson = objectMapper.writeValueAsString(sampleCustomer);

        //Then
        mockMvc.perform(get("/rentals/statement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is(RENTAL_DAYS_INVALID.getCode())))
                .andExpect(jsonPath("$.message", is(RENTAL_DAYS_INVALID.getMessage())));
    }
}
