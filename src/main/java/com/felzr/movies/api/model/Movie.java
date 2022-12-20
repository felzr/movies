package com.felzr.movies.api.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class Movie {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private Integer launchYear;
    @NotNull
    private String title;
    @NotNull
    private String studios;
    @NotNull
    private String produces;
    private String winner;

    public Movie(Integer launchYear, String title, String studios, String produces, String winner) {
        this.launchYear = launchYear;
        this.title = title;
        this.studios = studios;
        this.produces = produces;
        this.winner = winner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(Integer year) {
        this.launchYear = year;
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

    public String getProduces() {
        return produces;
    }

    public void setProduces(String produces) {
        this.produces = produces;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
