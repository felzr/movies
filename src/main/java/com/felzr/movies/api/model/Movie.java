package com.felzr.movies.api.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Date yearWinner;
    @NotNull
    private String title;
    @NotNull
    private String studios;
    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private List<Producer> producers;
    private boolean winner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getYearWinner() {
        return yearWinner;
    }

    public void setYearWinner(Date launchYear) {
        this.yearWinner = launchYear;
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

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    public boolean getWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }


    public Movie(Integer id, Date yearWinner, String title, String studios, List<Producer> producers, boolean winner) {
        this.id = id;
        this.yearWinner = yearWinner;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    public Movie(Date yearWinner, String title, String studios, List<Producer> producers, boolean winner) {
        this.yearWinner = yearWinner;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }


    public Movie() {
    }


}
