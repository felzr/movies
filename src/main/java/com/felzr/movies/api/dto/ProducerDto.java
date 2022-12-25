package com.felzr.movies.api.dto;

import com.sun.istack.NotNull;

public class ProducerDto {
    private Integer id;
    private String name;

    public ProducerDto(String name) {
        this.name = name;
    }

    public ProducerDto(Integer id, String name) {
        this.id = id;
        this.name = name;
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

}
