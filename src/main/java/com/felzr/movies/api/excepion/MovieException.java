package com.felzr.movies.api.excepion;

public class MovieException extends RuntimeException {
    public MovieException(String errorMessage) {
        super(errorMessage);
    }
}
