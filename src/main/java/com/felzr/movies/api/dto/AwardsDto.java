package com.felzr.movies.api.dto;

public class AwardsDto {
    private String producer;
    private Integer interval;
    private Integer previusWin;
    private Integer followingWin;

    public AwardsDto(String producer, Integer interval, Integer previusWin, Integer followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previusWin = previusWin;
        this.followingWin = followingWin;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getPreviusWin() {
        return previusWin;
    }

    public void setPreviusWin(Integer previusWin) {
        this.previusWin = previusWin;
    }

    public Integer getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Integer followingWin) {
        this.followingWin = followingWin;
    }
}
