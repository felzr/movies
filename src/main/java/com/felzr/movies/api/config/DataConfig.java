package com.felzr.movies.api.config;

import com.felzr.movies.api.model.Movie;
import com.felzr.movies.api.repository.MoviesRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    @Autowired
    private MoviesRepository repository;

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            repository.save(new Movie(1980, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", "yes"));
        };
    }
}
