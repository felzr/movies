package com.felzr.movies.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieDto {


    @JsonProperty("id")
    private Integer id;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("title")
    private String title;
    @JsonProperty("studios")
    private String studios;
    @JsonProperty("producer")
    private String producer;
    @JsonProperty("winner")
    private String winner;

    public MovieDto(Integer id, Integer year, String title, String studios, String producer, String winner) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producer = producer;
        this.winner = winner;
    }

    public MovieDto(Integer year, String title, String studios, String producer, String winner) {
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }


}
