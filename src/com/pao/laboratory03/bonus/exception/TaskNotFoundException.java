package com.pao.laboratory03.bonus.exception;

// Clasa trebuie să aibă un nume UNIC, nu "RuntimeException"
public class TaskNotFoundException extends java.lang.RuntimeException {
    public TaskNotFoundException(String message) {
        super(message); // Aici se apelează constructorul din Java Standard Library
    }
}