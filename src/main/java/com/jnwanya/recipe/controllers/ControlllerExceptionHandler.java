package com.jnwanya.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Jnwanya on
 * Fri, 12 Jan, 2018.
 */
@Slf4j
@ControllerAdvice
public class ControlllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception exception){
        log.error("Exception message", exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("400Error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
