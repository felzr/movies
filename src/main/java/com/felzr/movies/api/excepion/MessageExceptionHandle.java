package com.felzr.movies.api.excepion;

import java.util.Date;

public class MessageExceptionHandle {

    private Date date;
    private Integer status;
    private String message;

    public MessageExceptionHandle(Date date, Integer status, String message) {
        this.date = date;
        this.status = status;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
