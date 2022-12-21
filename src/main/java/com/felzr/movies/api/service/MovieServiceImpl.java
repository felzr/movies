package com.felzr.movies.api.service;

import com.felzr.movies.api.dto.MovieDto;
import com.felzr.movies.api.model.Movie;
import com.felzr.movies.api.repository.MoviesRepository;
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
        List<MovieDto> dtos = moviesRepository.findAll().stream().map(movie -> new MovieDto(movie.getId(), movie.getLaunchYear(), movie.getTitle(), movie.getStudios(), movie.getProducer(), movie.getWinner())).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public MovieDto getFMovieDtById(Integer id) {
        Optional<Movie> movie = moviesRepository.findById(id);
        return new MovieDto(movie.get().getLaunchYear(), movie.get().getTitle(), movie.get().getStudios(), movie.get().getProducer(), movie.get().getWinner());
    }

    @Override
    public void saveMovie(MovieDto movie) {
        moviesRepository.save(new Movie(movie.getYear(), movie.getTitle(), movie.getStudios(), movie.getProducer(), movie.getWinner()));
    }

    @Override
    public void deleteMovie(Integer id) {
        moviesRepository.deleteById(id);
    }

    @Override
    public void editMovie(MovieDto movie) {
        moviesRepository.save(new Movie(movie.getId(), movie.getYear(), movie.getTitle(), movie.getStudios(), movie.getProducer(), movie.getWinner()));

    }

    @Override
    public void saveAllMovies(List<MovieDto> movies) {
        moviesRepository.saveAll(movies.stream().map(movie -> new Movie(movie.getYear(), movie.getTitle(), movie.getStudios(), movie.getProducer(), movie.getWinner())).collect(Collectors.toList()));
    }
}
