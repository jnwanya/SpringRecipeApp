package com.jnwanya.recipe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Jnwanya on
 * Thu, 11 Jan, 2018.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(){
        super();
    }

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
