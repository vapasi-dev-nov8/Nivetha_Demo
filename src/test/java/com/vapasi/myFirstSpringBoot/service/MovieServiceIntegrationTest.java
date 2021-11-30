package com.vapasi.myFirstSpringBoot.service;

import com.vapasi.myFirstSpringBoot.dto.MovieDto;
import com.vapasi.myFirstSpringBoot.entity.MovieEntity;
import com.vapasi.myFirstSpringBoot.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MovieServiceIntegrationTest {
    @Autowired
    MovieService moviesService;

    @Autowired
    MovieRepository moviesRepository;

    @Test
    public void getAllMoviesAddedToList() {
        moviesRepository.deleteAll();

        List<MovieEntity> allMovies = new ArrayList<>();
        allMovies.add(new MovieEntity(null, "pirates of caribbean", "Johny Depp", "Gore Verbinsky"));
        allMovies.add(new MovieEntity( null, "The Fight club", "Brad pitt", "David Fincher"));
        allMovies.add(new MovieEntity( null, "Interstellar", "Mathew McConaughey", "Christopher Nolan"));
        allMovies.add(new MovieEntity( null, "pulp fiction", "Samuel L Johnson", "Quintine torentino"));
        moviesRepository.saveAll(allMovies);


        List<MovieDto> actualMovies = moviesService.getAllMovies();

        assertEquals(4, actualMovies.size());
    }

    @Test
    public void getEmptyMoviesWhenNoMoviesAddedToList() {
        moviesRepository.deleteAll();

        List<MovieDto> allMovies = moviesService.getAllMovies();

        assertEquals(0, allMovies.size());
    }

    @Test
    void shouldSaveAndRetrieveMovie() {
        // Given
        moviesRepository.deleteAll();

        // When
        MovieEntity movieEntity = new MovieEntity(null, "test-movie", "test-actor", "test-director");
        MovieEntity savedEntity = moviesRepository.save(movieEntity);

        // Then
        MovieDto expectedMovie = new MovieDto(savedEntity.getId() ,"test-movie", "test-actor", "test-director");
        assertEquals(expectedMovie, moviesService.getMovie(savedEntity.getId()).get());
    }
}
