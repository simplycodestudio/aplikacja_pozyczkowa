package org.simplycodestudio.TWINO.Task.loanapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooBigAmountAtNightZone extends RuntimeException{

    public TooBigAmountAtNightZone(String message) {
        super(message);
    }
}
