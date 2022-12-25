package com.felzr.movies.api.service;

import com.felzr.movies.api.dto.MovieDto;
import com.felzr.movies.api.model.Movie;
import com.felzr.movies.api.repository.MoviesRepository;
import com.felzr.movies.api.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public List<MovieDto> getAllMoviesDto() {
        List<MovieDto> dtos = moviesRepository.findAll().stream().map(movie -> new MovieDto(movie.getId(), DateUtils.getDataDMA(movie.getYearWinner()), movie.getTitle(), movie.getStudios(), null, movie.getWinner())).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public MovieDto getFMovieDtById(Integer id) {
        Optional<Movie> movie = moviesRepository.findById(id);
        return new MovieDto(DateUtils.getDataDMA(movie.get().getYearWinner()), movie.get().getTitle(), movie.get().getStudios(), null, movie.get().getWinner());
    }

    @Override
    public void saveMovie(MovieDto movie) {
        moviesRepository.save(new Movie(DateUtils.getDatefromYear(movie.getYear()), movie.getTitle(), movie.getStudios(), null, movie.getWinner()));
    }

    @Override
    public void deleteMovie(Integer id) {
        moviesRepository.deleteById(id);
    }

    @Override
    public void editMovie(MovieDto movie) {
        moviesRepository.save(new Movie(movie.getId(), DateUtils.getDatefromYear(movie.getYear()), movie.getTitle(), movie.getStudios(), null, movie.getWinner()));

    }

    @Override
    public void saveAllMovies(List<MovieDto> movies) {
        moviesRepository.saveAll(movies.stream().map(movie -> new Movie(DateUtils.getDatefromYear(movie.getYear()), movie.getTitle(), movie.getStudios(),null, movie.getWinner())).collect(Collectors.toList()));
    }
}
