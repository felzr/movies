package com.felzr.movies.api.config;

import com.felzr.movies.api.dto.MovieCsv;
import com.felzr.movies.api.model.Movie;
import com.felzr.movies.api.repository.MoviesRepository;
import com.felzr.movies.api.util.ReadCsv;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DataConfig {

    @Autowired
    private MoviesRepository moviesRepository;

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            ReadCsv readCsv = new ReadCsv();
            List<MovieCsv> movieCsvList = readCsv.convertCsvToDto();
            List<Movie> movies = movieCsvList.stream().map(movieCsv -> new Movie(movieCsv.getYear(), movieCsv.getTitle(), movieCsv.getStudios(), movieCsv.getProducer(), movieCsv.getWinner())).collect(Collectors.toList());
            moviesRepository.saveAll(movies);
        };
    }
}
