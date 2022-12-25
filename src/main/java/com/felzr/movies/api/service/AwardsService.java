package com.felzr.movies.api.service;

import com.felzr.movies.api.dto.AwardsDto;
import com.felzr.movies.api.dto.AwardsIntervalDto;

import java.util.List;

public interface AwardsService {
    AwardsIntervalDto getAwardsIntervalDto();

    List<AwardsDto> getAllAwardsDtos();
}
