package com.vapasi.myFirstSpringBoot.controller;

import com.vapasi.myFirstSpringBoot.dto.MovieDto;
import com.vapasi.myFirstSpringBoot.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> allMovies = movieService.getAllMovies();
        return ResponseEntity.ok().body(allMovies);
    }

    @PostMapping("/")
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto) {
        MovieDto savedMovieDto = movieService.saveMovie(movieDto);
        return new ResponseEntity<MovieDto>(savedMovieDto, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Integer id) {
        Optional<MovieDto> movieDto = movieService.getMovie(id);
        return movieDto.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MovieDto> updateEmployee( @PathVariable Integer id, @RequestBody MovieDto movieDto) {
        MovieDto updatedMovie = movieService.updateMovie(id, movieDto);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/moviesByActors")
    public ResponseEntity<List<MovieDto>> findMoviesByActors(@RequestParam("actorName") List<String> actorNames) {
        List<MovieDto> movieList = movieService.findMoviesByActors(actorNames);
        return ResponseEntity.ok().body(movieList);
    }

    @GetMapping(value = "/moviesByDirectors")
    public ResponseEntity<List<MovieDto>> findMoviesByDirectors(@RequestParam("directorName") List<String> directorNames) {
        List<MovieDto> movieList = movieService.findMoviesByDirectors(directorNames);
        return ResponseEntity.ok().body(movieList);
    }

    @GetMapping(value = "/moviesByNames")
    public ResponseEntity<List<MovieDto>> findMoviesByNames(@RequestParam("name") List<String> movieNames) {
        List<MovieDto> movieList = movieService.findMoviesByNames(movieNames);
        return ResponseEntity.ok().body(movieList);
    }
}
