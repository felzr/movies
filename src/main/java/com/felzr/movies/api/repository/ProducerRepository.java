package com.felzr.movies.api.repository;

import com.felzr.movies.api.model.Producer;
import com.felzr.movies.api.model.WinningProducerView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer, Integer> {
    List<Producer> findAllByYearWinnerIsNotNull();

}
