package com.vapasi.myFirstSpringBoot.service;

import com.vapasi.myFirstSpringBoot.dto.MovieDto;
import com.vapasi.myFirstSpringBoot.entity.MovieEntity;
import com.vapasi.myFirstSpringBoot.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDto> getAllMovies() {
        List<MovieDto> allMovies = new ArrayList<>();
        for(MovieEntity entity : movieRepository.findAll()) {
            allMovies.add(MovieDto.dtoFrom(entity));
        }
        return allMovies;

    }

    public MovieDto saveMovie(MovieDto movieDto) {
        MovieEntity movieEntity = MovieEntity.entityFrom(movieDto);
        return MovieDto.dtoFrom(movieRepository.save(movieEntity));
    }

    public Optional<MovieDto> getMovie(Integer id) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        return movieEntity.map(MovieDto::dtoFrom);
    }

    public void deleteMovie(Integer id) {
        if (movieRepository.findById(id).get().getId().equals(id)){
            movieRepository.deleteById(id);
        }
        //Need to handle custom exception..
    }

    public MovieDto updateMovie(Integer id, MovieDto movieDto) {
        MovieEntity movieToUpdate = movieRepository.findById(id).orElse(new MovieEntity());
        movieToUpdate.setName(movieDto.getName());
        movieToUpdate.setActorName(movieDto.getActorName());
        movieToUpdate.setDirectorName(movieDto.getDirectorName());
        return MovieDto.dtoFrom( movieRepository.save(movieToUpdate));
    }

    public List<MovieDto> findMoviesByActors(List<String> actorNames) {
       List<MovieEntity> movieEntityList = movieRepository.findByActorNameIn(actorNames);
       return movieEntityList.stream().map(MovieDto::dtoFrom).collect(Collectors.toList());
    }

    public List<MovieDto> findMoviesByDirectors(List<String> directorNames) {
        List<MovieEntity> movieEntityList = movieRepository.findByDirectorNameIn(directorNames);
        return movieEntityList.stream().map(MovieDto::dtoFrom).collect(Collectors.toList());
    }

    public List<MovieDto> findMoviesByNames(List<String> movieNames) {
        List<MovieEntity> movieEntityList = movieRepository.findByNameIn(movieNames);
        return movieEntityList.stream().map(MovieDto::dtoFrom).collect(Collectors.toList());
    }
}
