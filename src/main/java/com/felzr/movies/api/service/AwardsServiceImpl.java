package com.felzr.movies.api.service;

import com.felzr.movies.api.dto.AwardsDto;
import com.felzr.movies.api.dto.AwardsIntervalDto;
import com.felzr.movies.api.model.WinningProducerView;
import com.felzr.movies.api.repository.MoviesRepository;
import com.felzr.movies.api.repository.ProducerRepository;
import com.felzr.movies.api.repository.WinningProducerViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AwardsServiceImpl implements AwardsService {

    MoviesRepository moviesRepository;

    ProducerRepository producerRepository;

    WinningProducerViewRepository winningProducerViewRepository;

    @Autowired
    public AwardsServiceImpl(MoviesRepository moviesRepository, ProducerRepository producerRepository, WinningProducerViewRepository winningProducerViewRepository) {
        this.moviesRepository = moviesRepository;
        this.producerRepository = producerRepository;
        this.winningProducerViewRepository = winningProducerViewRepository;
    }

    @Override
    public AwardsIntervalDto getAwardsIntervalDto() {
        List<WinningProducerView> winningList = winningProducerViewRepository.findAll();
        return filterAwardsInterval(winningList);
    }

    private AwardsIntervalDto filterAwardsInterval(List<WinningProducerView> winningList) {
        List<AwardsDto> awardList = checkIntervalWinner(winningList);
        return filterMaxAnMinAwards(awardList);
    }

    private AwardsIntervalDto filterMaxAnMinAwards(List<AwardsDto> awardList) {
        Comparator<AwardsDto> comparator = Comparator.comparing(AwardsDto::getInterval);
        List<AwardsDto> min = awardList.stream().sorted(comparator.thenComparingInt(object -> object.getInterval())).limit(2).collect(Collectors.toList());
        List<AwardsDto> max = awardList.stream().sorted(comparator.thenComparingInt(object -> object.getInterval()).reversed()).limit(2).collect(Collectors.toList());

        return new AwardsIntervalDto(min, max);
    }

    private List<AwardsDto> checkIntervalWinner(List<WinningProducerView> winningProducerViews) {
        List<AwardsDto> awardList = new ArrayList<>();
        for (int i = 0; i < winningProducerViews.size(); i++) {
            WinningProducerView winner, nextWinner;
            winner = winningProducerViews.get(i);
            if (i + 1 < winningProducerViews.size()) {
                nextWinner = winningProducerViews.get(i + 1);
                if (winner.getName().equals(nextWinner.getName())) {
                    awardList.add(new AwardsDto(winner.getName(), nextWinner.getWinner() - winner.getWinner(), winner.getWinner(), nextWinner.getWinner()));
                }
            }
        }
        return awardList;
    }

}
