package com.andreappereira.main.exceptions;

public class ApplyFoundException extends RuntimeException {
    public ApplyFoundException () {
        super("Candidate application exists.");
    }
}
