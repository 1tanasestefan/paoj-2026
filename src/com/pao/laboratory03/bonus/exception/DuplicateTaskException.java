package com.pao.laboratory03.bonus.exception;

// Trebuie să extindă RuntimeException pentru a deveni o excepție validă
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException(String message) {
        // Acum super(message) apelează corect constructorul lui RuntimeException
        super(message);
    }
}