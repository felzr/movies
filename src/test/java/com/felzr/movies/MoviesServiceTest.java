package com.felzr.movies;

import com.felzr.movies.api.config.DataConfig;
import com.felzr.movies.api.dto.AwardsIntervalDto;
import com.felzr.movies.api.dto.MovieDto;
import com.felzr.movies.api.model.WinningProducerView;
import com.felzr.movies.api.repository.WinningProducerViewRepository;
import com.felzr.movies.api.service.AwardsService;
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
    @Autowired
    AwardsService awardsService;
    @Autowired
    WinningProducerViewRepository winnerViewRepository;

    ReadCsv readCsv;

    private static String FIRST_MOVIE = "Can't Stop the Music";
    private static String MOCK_PRDUCER = "Matthew Vaughn";
    private static Integer YEAR_WINNER_MOCK = 2028;

    @Before
    public void setup() {
        DataConfig dataConfig = new DataConfig();
        dataConfig.sendDatabase();
        readCsv = new ReadCsv();
        createMockWinnerAward();

    }

    private void createMockWinnerAward() {
        winnerViewRepository.save(new WinningProducerView(MOCK_PRDUCER, YEAR_WINNER_MOCK));
    }

    @Test
    public void validate_if_data_form_inserted() throws IOException {
        List<MovieDto> movieList = movieService.getAllMoviesDto();
        assertEquals(readCsv.getCountLinesCsv(), movieList.size());
        assertEquals(FIRST_MOVIE, movieList.get(0).getTitle());
    }

    @Test
    public void validate_return_interval_producers_winners() {
        AwardsIntervalDto intervalDto = awardsService.getAwardsIntervalDto();
        assertEquals(MOCK_PRDUCER, intervalDto.getMax().get(1).getProducer());
    }

}
