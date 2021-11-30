package com.vapasi.myFirstSpringBoot.service;

import com.vapasi.myFirstSpringBoot.dto.MovieDto;
import com.vapasi.myFirstSpringBoot.entity.MovieEntity;
import com.vapasi.myFirstSpringBoot.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    void getAllMoviesAddedToList(){
        // Given
        List<MovieEntity> allMovies = new ArrayList<>();
        allMovies.add(new MovieEntity(1, "pirates of caribbean", "Johny Depp", "Gore Verbinsky"));
        allMovies.add(new MovieEntity(2, "The Fight club", "Brad pitt", "David Fincher"));
        allMovies.add(new MovieEntity(3, "Interstellar", "Mathew McConaughey", "Christopher Nolan"));
        allMovies.add(new MovieEntity(4, "pulp fiction", "Samuel L Johnson", "Quintine torentino"));
        when(movieRepository.findAll()).thenReturn(allMovies);

        // When
        List<MovieDto> actualMovies = movieService.getAllMovies();

        // Then
        List<MovieDto> expectedMovies = Arrays.asList(
                new MovieDto(1, "pirates of caribbean", "Johny Depp", "Gore Verbinsky"),
                new MovieDto(2, "The Fight club", "Brad pitt", "David Fincher"),
                new MovieDto(3, "Interstellar", "Mathew McConaughey", "Christopher Nolan"),
                new MovieDto(4, "pulp fiction", "Samuel L Johnson", "Quintine torentino"));

        verify(movieRepository, times(1)).findAll();
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void getEmptyListWhenNoMoviesAdded(){
        // Given
        List<MovieEntity> allMovies = new ArrayList<>();
        when(movieRepository.findAll()).thenReturn(allMovies);

        // When
        movieService.getAllMovies();

        // Then
        verify(movieRepository, times(1)).findAll();
        assertEquals(allMovies.size(), 0);
    }

    @Test
    void shouldGetMovie() {
        // Given
        MovieEntity movieEntity = new MovieEntity(6, "test-movie", "test-actor", "test-director");
        when(movieRepository.findById(6)).thenReturn(Optional.of(movieEntity));

        // When
        Optional<MovieDto> actualMovie = movieService.getMovie(6);
        MovieDto expectedMovie = new MovieDto(6, "test-movie", "test-actor", "test-director");

        // Then
        assertEquals(expectedMovie, actualMovie.get());
        verify(movieRepository, times(1)).findById(6);
    }

    @Test
    void shouldSaveMovie() {
        // Given
        MovieDto movieDto = new MovieDto("test-movie", "test-actor", "test-director");
        MovieEntity savedMovieEntity = new MovieEntity(6, "test-movie", "test-actor", "test-director");
        when(movieRepository.save(any())).thenReturn(savedMovieEntity);

        // When
        movieService.saveMovie(movieDto);

        // Then
        ArgumentCaptor<MovieEntity> movieEntityArgumentCaptor = ArgumentCaptor.forClass(MovieEntity.class);
        verify(movieRepository).save(movieEntityArgumentCaptor.capture());
        MovieEntity expectedMovie = new MovieEntity(6, "test-movie", "test-actor", "test-director");
        MovieEntity actualMovie = movieEntityArgumentCaptor.getValue();
        assertEquals(expectedMovie, actualMovie);
    }

}