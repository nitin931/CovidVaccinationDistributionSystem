package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Exceptions;

public class WrongCredentials extends RuntimeException{
    public WrongCredentials(String msg){
        super(msg);
    }
}
