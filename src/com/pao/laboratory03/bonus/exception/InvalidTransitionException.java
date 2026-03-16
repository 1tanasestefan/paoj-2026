package com.pao.laboratory03.bonus.exception;

import com.pao.laboratory03.bonus.model.Status;

// ADAUGĂ: extends RuntimeException
public class InvalidTransitionException extends RuntimeException {

    public InvalidTransitionException(Status from, Status to) {
        // Acum super() apelează constructorul lui RuntimeException
        super("Nu se poate trece din " + from + " în " + to);
    }
}