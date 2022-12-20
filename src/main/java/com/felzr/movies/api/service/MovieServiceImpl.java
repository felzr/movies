package com.felzr.movies.api.service;

import com.felzr.movies.api.dto.MovieDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Override
    public List<MovieDto> getAllMoviesDto() {
        return null;
    }

    @Override
    public MovieDto getFMovieDtById(Integer id) {
        return null;
    }

    @Override
    public void saveMovie(MovieDto movie) {

    }

    @Override
    public void deleteMovie(Integer id) {

    }

    @Override
    public void editMovie(MovieDto movie) {

    }

    @Override
    public void saveAllMovies(List<MovieDto> movies) {

    }
}
