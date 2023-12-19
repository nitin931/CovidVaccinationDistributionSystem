package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Exceptions;

public class PatientDoesNotExistException extends RuntimeException{
    public PatientDoesNotExistException(String msg){
        super(msg);
    }
}
