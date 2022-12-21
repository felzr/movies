package com.felzr.movies.api.controller;

import com.felzr.movies.api.dto.MovieDto;
import com.felzr.movies.api.enums.Messages;
import com.felzr.movies.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping(value = "/save-movie")
    public ResponseEntity<?> saveMovie(@RequestBody MovieDto movieDto) {
        movieService.saveMovie(movieDto);
        return new ResponseEntity(Messages.SAVE_MOVIE_SUCESS.getStatus(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/find-movie-by-id/{id}")
    public ResponseEntity<MovieDto> findMovieById(Integer id) {
        MovieDto movieDto = movieService.getFMovieDtById(id);
        return new ResponseEntity(movieDto, HttpStatus.OK);
    }

    @GetMapping(value = "/find-all-movies")
    public ResponseEntity<MovieDto> findAllMovie() {
        List<MovieDto> allMoviesDto = movieService.getAllMoviesDto();
        return new ResponseEntity(allMoviesDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-movie/{id}")
    public ResponseEntity<?> deletarPautaPorId(Integer id) {
        movieService.deleteMovie(id);
        return new ResponseEntity(Messages.DELETE_MOVIE_SUCESS.toString(), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/edit-movie")
    public ResponseEntity<?> abrirVotacaoPauta(@RequestBody MovieDto movie) {
        movieService.editMovie(movie);
        return new ResponseEntity(Messages.EDIT_MOVIE_SUCESS.getStatus(), HttpStatus.ACCEPTED);
    }
}
