package com.felzr.movies.api.service;

import com.felzr.movies.api.dto.MovieDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovieService {
    @Transactional
    List<MovieDto> getAllMoviesDto();
    @Transactional
    MovieDto getMovieDtById(Integer id);
    @Transactional
    void saveMovie(MovieDto movie);
    @Transactional
    void deleteMovie(Integer id);
    @Transactional
    void editMovie(MovieDto movie);
    @Transactional
    void saveAllMovies(List<MovieDto> movies);


}
