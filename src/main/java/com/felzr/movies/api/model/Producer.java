package com.felzr.movies.api.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "producer")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;

    private Integer yearWinner;

    public Producer(Integer id, String name, Integer yearWinner) {
        this.id = id;
        this.name = name;
        this.yearWinner = yearWinner;
    }

    public Producer(String name) {
        this.name = name;
    }

    public Producer() {
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

    public Integer getYearWinner() {
        return yearWinner;
    }

    public void setYearWinner(Integer yearWinner) {
        this.yearWinner = yearWinner;
    }


}
