package com.felzr.movies.api.repository;

import com.felzr.movies.api.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends CrudRepository<Movie, Integer> {
}
