package com.andreappereira.main.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Company not found.");
    }
}
