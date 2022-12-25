package com.felzr.movies.api.repository;

import com.felzr.movies.api.model.WinningProducerView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WinningProducerViewRepository extends JpaRepository<WinningProducerView, Integer> {
    List<WinningProducerView> findAll();
}
