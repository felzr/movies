package com.felzr.movies.api.dto;

import java.util.List;

public class AwardsIntervalDto {
    List<AwardsDto> min;
    List<AwardsDto> max;

    public AwardsIntervalDto(List<AwardsDto> min, List<AwardsDto> max) {
        this.min = min;
        this.max = max;
    }

    public List<AwardsDto> getMin() {
        return min;
    }

    public void setMin(List<AwardsDto> min) {
        this.min = min;
    }

    public List<AwardsDto> getMax() {
        return max;
    }

    public void setMax(List<AwardsDto> max) {
        this.max = max;
    }
}
