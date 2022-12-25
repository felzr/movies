package com.felzr.movies.api.controller;

import com.felzr.movies.api.dto.AwardsIntervalDto;
import com.felzr.movies.api.service.AwardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/awards")
public class AwardsController {
    @Autowired
    private AwardsService awardsService;

    @GetMapping(value = "/interval")
    public ResponseEntity<AwardsIntervalDto> findAllMovie() {
        AwardsIntervalDto awards = awardsService.getAwardsIntervalDto();
        return new ResponseEntity(awards, HttpStatus.OK);
    }
}
