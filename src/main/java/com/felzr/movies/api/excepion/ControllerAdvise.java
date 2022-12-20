package com.felzr.movies.api.excepion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice(basePackages = "com.felzr.movies.api.controller")
public class ControllerAdvise {
    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<MessageExceptionHandle> pautaExpection(MovieException movieException) {
        MessageExceptionHandle message = new MessageExceptionHandle(new Date(), HttpStatus.NOT_FOUND.value(), movieException.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
