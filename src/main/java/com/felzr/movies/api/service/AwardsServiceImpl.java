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

    @Override
    public List<AwardsDto> getAllAwardsDtos() {
        return null;
    }

    private AwardsIntervalDto filterAwardsInterval(List<WinningProducerView> winningList) {
        List<AwardsDto> awardList = checkIntervalWinner(winningList);
        return filterMaxAnMinAwards(awardList);
    }

    private AwardsIntervalDto filterMaxAnMinAwards(List<AwardsDto> awardList) {
        Comparator<AwardsDto> comparator = Comparator.comparing(AwardsDto::getInterval);
        return getAwardsObject(awardList.stream().min(comparator).get(), awardList.stream().max(comparator).get());
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

    private AwardsIntervalDto getAwardsObject(AwardsDto min, AwardsDto max) {
        List<AwardsDto> minList = new ArrayList<>();
        List<AwardsDto> maxList = new ArrayList<>();
        minList.add(min);
        maxList.add(max);
        return new AwardsIntervalDto(minList, maxList);
    }
}
