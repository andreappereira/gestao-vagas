package com.andreappereira.main.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuário já existente.");
    }
}
