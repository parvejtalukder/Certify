package com.pht.certify.controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoResourceFoundException {
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public String handle404() {
        return "error/404";
    }
}