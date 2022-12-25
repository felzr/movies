package com.felzr.movies.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieDto {


    @JsonProperty("id")
    private Integer id;
    @JsonProperty("year")
    private String year;
    @JsonProperty("title")
    private String title;
    @JsonProperty("studios")
    private String studios;
    @JsonProperty("producer")
    private List<ProducerDto> producer;
    @JsonProperty("winner")
    private boolean winner;

    public MovieDto(Integer id, String year, String title, String studios, List<ProducerDto> producer, boolean winner) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producer = producer;
        this.winner = winner;
    }

    public MovieDto(String year, String title, String studios, List<ProducerDto> producer, boolean winner) {
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producer = producer;
        this.winner = winner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public boolean getWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public List<ProducerDto> getProducer() {
        return producer;
    }

    public void setProducer(List<ProducerDto> producer) {
        this.producer = producer;
    }


}
