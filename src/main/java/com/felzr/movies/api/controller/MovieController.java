package com.felzr.movies.api.controller;

import com.felzr.movies.api.dto.MovieDto;
import com.felzr.movies.api.enums.Messages;
import com.felzr.movies.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<?> saveMovie(@RequestBody MovieDto movieDto) {
        movieService.saveMovie(movieDto);
        return new ResponseEntity(Messages.SAVE_MOVIE_SUCESS.getStatus(), HttpStatus.OK);
    }
}
