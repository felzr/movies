package com.felzr.movies;

import com.felzr.movies.api.config.DataConfig;
import com.felzr.movies.api.dto.MovieDto;
import com.felzr.movies.api.service.MovieService;
import com.felzr.movies.api.util.ReadCsv;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesServiceTest {
    @Autowired
    MovieService movieService;

    ReadCsv readCsv;

    private static String firtsMovie= "Can't Stop the Music";
    @Before
    public void setup() {
        DataConfig dataConfig = new DataConfig();
        dataConfig.sendDatabase();
        readCsv = new ReadCsv();

    }

    @Test
    public void validate_if_data_form_inserted() throws IOException {
        List<MovieDto> movieList = movieService.getAllMoviesDto();
        assertEquals(readCsv.getCountLinesCsv(), movieList.size());
        assertEquals(firtsMovie, movieList.get(0).getTitle());
    }

}
