package org.simplycodestudio.TWINO.Task.loanapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class TooManyProposalsFromTheSameAdressException extends RuntimeException {

    public TooManyProposalsFromTheSameAdressException(String message) {
        super(message);
    }
}
