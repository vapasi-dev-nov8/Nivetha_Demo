package com.vapasi.myFirstSpringBoot.service;

import com.vapasi.myFirstSpringBoot.dto.MovieDto;
import com.vapasi.myFirstSpringBoot.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {
    MovieService movieService;
    MovieRepository movieRepository;


    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        movieService =new MovieService(movieRepository);
    }

//    @Test
//    void getAllMoviesAddedToList(){
//        List<MovieDto> movieList = new ArrayList<MovieDto>();
//        movieList.add(new MovieDto(1,"Inception","DI Caprio","Stephen"));
//        movieList.add(new MovieDto(2,"MI","Tom Hank","Stephen"));
//        movieList.add(new MovieDto(3,"The Godfather","Marlon Brando","Stephen"));
//
//        when(movieRepository.findAll()).thenReturn(movieList);
//
//        List<Movie> totalMovies = movieService.getMovies();
//        verify(movieRepository,times(1)).getAllMovies();
//        assertEquals(movieList,totalMovies);
//    }
//
//    @Test
//    void getEmptyListWhenNoMoviesAdded(){
//        List<Movie> movieList = new ArrayList<Movie>();
//
//        when(movieRepository.getAllMovies()).thenReturn(movieList);
//
//        List<Movie> totalMovies = movieService.getMovies();
//        verify(movieRepository,times(1)).getAllMovies();
//        assertEquals(movieList.size(),totalMovies.size());
//    }
//
//    @Test
//    void shouldSaveMovie(){
//        Movie requestMovie = mock(Movie.class);
//        Movie savedMovie = mock(Movie.class);
//
//        when(movieRepository.saveMovie(requestMovie)).thenReturn(savedMovie);
//
//        Movie actualMovie = movieService.saveMovie(requestMovie);
//
//        assertEquals(savedMovie,actualMovie);
//    }

}