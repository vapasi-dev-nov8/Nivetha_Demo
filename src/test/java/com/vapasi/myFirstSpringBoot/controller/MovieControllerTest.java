package com.vapasi.myFirstSpringBoot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vapasi.myFirstSpringBoot.dto.MovieDto;
import com.vapasi.myFirstSpringBoot.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService moviesService;

    @Test
    void shouldExpectOkForMoviesList() throws Exception {
        mockMvc.perform(get("/api/v1/movies/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(moviesService, times(1)).getAllMovies();
    }

    @Test
    void shouldExpectNotFoundErrorWhenUrlIsWrong () throws Exception {
        mockMvc.perform(get("/api/v1/movies")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(moviesService, times(0)).getAllMovies();
    }

    @Test
    void shouldExpectMovieCreatedifMovieIsSaved () throws Exception {

        MovieDto requestMovie = new MovieDto(null, "Movie Name", "Actor Name", "Director Name");
        MovieDto savedMovie = new  MovieDto(1, "Movie Name", "Actor Name", "Director Name");

        when(moviesService.saveMovie(requestMovie)).thenReturn(savedMovie);

        mockMvc.perform(post("/api/v1/movies/create")
                        .content(asJsonString(requestMovie))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(savedMovie)));
        verify(moviesService, times(1)).saveMovie(requestMovie);
    }


    private String asJsonString(MovieDto movie) {
        try {
            return new ObjectMapper().writeValueAsString(movie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}