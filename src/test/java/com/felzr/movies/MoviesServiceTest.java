package com.felzr.movies;

import com.felzr.movies.api.config.DataConfig;
import com.felzr.movies.api.dto.MovieDto;
import com.felzr.movies.api.model.Movie;
import com.felzr.movies.api.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesServiceTest {
    @Autowired
    MovieService movieService;

    private static String firtsMovie= "Can't Stop the Music";
    @Before
    public void setup() {
        DataConfig dataConfig = new DataConfig();
        dataConfig.sendDatabase();
    }

    @Test
    public void validate_if_data_form_inserted() {
        List<MovieDto> movieList = movieService.getAllMoviesDto();
        assertEquals(206, movieList.size());
        assertEquals(firtsMovie, movieList.get(0).getTitle());
    }

}
