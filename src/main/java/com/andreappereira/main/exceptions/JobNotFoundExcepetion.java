package com.andreappereira.main.exceptions;


public class JobNotFoundExcepetion extends RuntimeException {
    public JobNotFoundExcepetion() {
        super("Job not found.");
    }
}
