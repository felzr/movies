package com.felzr.movies.api.repository;

import com.felzr.movies.api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movie, Integer> {
}
