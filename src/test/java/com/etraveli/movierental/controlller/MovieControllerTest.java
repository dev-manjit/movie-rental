package com.etraveli.movierental.controlller;

import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.service.MovieService;
import com.etraveli.movierental.service.rent.MovieCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterMovie_ShouldReturnSavedMovie() throws Exception {
        // Given
        Movie sampleMovie = new Movie("F001", "You've Got Mail", MovieCategory.REGULAR);

        // When
        when(movieService.saveMovie(any(Movie.class))).thenReturn(sampleMovie);

        // Convert the sample movie to JSON
        String movieJson = objectMapper.writeValueAsString(sampleMovie);

        // Then
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJson))
                .andExpect(status().isOk())
                .andExpect(content().json(movieJson));
        //And
        verify(movieService,times(1)).saveMovie(any(Movie.class));
    }

}

