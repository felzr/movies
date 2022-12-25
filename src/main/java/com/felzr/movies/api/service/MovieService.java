package com.felzr.movies.api.service;

import com.felzr.movies.api.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> getAllMoviesDto();

    MovieDto getFMovieDtById(Integer id);

    void saveMovie(MovieDto movie);

    void deleteMovie(Integer id);

    void editMovie(MovieDto movie);

    void saveAllMovies(List<MovieDto> movies);



}
