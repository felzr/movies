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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        for (MovieCsv movieCsv : csvList) {
            Movie movie = new Movie(DateUtils.getDatefromYear(movieCsv.getYear()), movieCsv.getTitle(), movieCsv.getStudios(), null, false);
            if (movieCsv.getWinner().equals(Winner.YES.getText())) {
                movie.setWinner(true);
            }
            List<String> prod = separateProducers(movieCsv.getProducer());
            List<Producer> producers = new ArrayList<>();
            for (String name : prod) {
                if (!name.isEmpty()) {
                    Producer producer = new Producer(name.trim());
                    producers.add(producer);
                    if (movie.getWinner()) {
                        producer.setYearWinner(Integer.valueOf(movieCsv.getYear()));
                    }
                }

            }
            movie.setProducers(producers);
            movieList.add(movie);
        }
        return movieList;
    }

    private List<String> separateProducers(String producer) {
        return Arrays.asList(producer.split((",|\\ and ")));
    }
}