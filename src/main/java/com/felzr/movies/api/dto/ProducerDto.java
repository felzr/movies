package com.felzr.movies.api.dto;

public class ProducerDto {
    private Integer id;
    private String name;
    private Integer winner;

    public ProducerDto(String name) {
        this.name = name;
    }

    public ProducerDto(Integer id, String name, Integer winner) {
        this.id = id;
        this.name = name;
        this.winner = winner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }


}
