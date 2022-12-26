package com.felzr.movies.api.util;

import com.felzr.movies.api.dto.ProducerDto;
import com.felzr.movies.api.model.Producer;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectConverter {
    public static List<ProducerDto> convertEntityToDtoProducer(List<Producer> producers) {
        List<ProducerDto> list = producers.stream().map(producer -> new ProducerDto(producer.getId(), producer.getName(), producer.getYearWinner())).collect(Collectors.toList());
        return list;
    }

    public static List<Producer> convertDtoProducertoEntity(List<ProducerDto> dto) {
        List<Producer> list = dto.stream().map(producerDto -> new Producer(producerDto.getName(), producerDto.getWinner())).collect(Collectors.toList());
        return list;
    }
}
