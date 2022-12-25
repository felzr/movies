package com.felzr.movies.api.config;

import com.felzr.movies.api.dto.MovieCsv;
import com.felzr.movies.api.enums.Winner;
import com.felzr.movies.api.model.Movie;
import com.felzr.movies.api.model.Producer;
import com.felzr.movies.api.repository.MoviesRepository;
import com.felzr.movies.api.service.AwardsService;
import com.felzr.movies.api.util.DateUtils;
import com.felzr.movies.api.util.ReadCsv;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.schema.property.BeanPropertyDefinitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DataConfig {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    AwardsService awardsService;


    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            ReadCsv readCsv = new ReadCsv();
            List<MovieCsv> movieCsvList = readCsv.convertCsvToDto();
            List<Movie> movies = convertCsvDtoEntity(movieCsvList);
            moviesRepository.saveAllAndFlush(movies);
        };
    }

    List<Movie> convertCsvDtoEntity(List<MovieCsv> csvList) {
        List<Movie> movieList = new ArrayList<>();
        csvList.forEach(movieCsv -> {
            Movie movie = createMoveiFromCsvObject(movieCsv);
            List<String> prod = separateProducers(movieCsv.getProducer());
            movie.setProducers(convertProducer(movie, prod, movieCsv));
            movieList.add(movie);
        });
        return movieList;
    }

    private List<Producer> convertProducer(Movie movie, List<String> producersNames, MovieCsv movieCsv) {
        List<Producer> producers = new ArrayList<>();
        producersNames.forEach(name -> {
            Producer producer = new Producer(name.trim());
            if (movie.getWinner()) {
                producer.setYearWinner(Integer.valueOf(movieCsv.getYear()));
            }
            producers.add(producer);
        });
        return producers;
    }

    private Movie createMoveiFromCsvObject(MovieCsv movieCsv) {
        Movie movie = new Movie(DateUtils.getDatefromYear(movieCsv.getYear()), movieCsv.getTitle(), movieCsv.getStudios(), null, false);
        if (movieCsv.getWinner().equals(Winner.YES.getText())) {
            movie.setWinner(true);
        }
        return movie;
    }

    private List<String> separateProducers(String producer) {
        List<String> listProducer = Arrays.asList(producer.split((",|\\ and ")));
        listProducer = listProducer.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        return listProducer;
    }
}