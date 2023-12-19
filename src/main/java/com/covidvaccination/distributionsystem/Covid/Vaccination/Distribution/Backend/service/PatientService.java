package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.service;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Request.PatientLoginDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Request.PatientSignupDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Exceptions.PatientDoesNotExistException;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Exceptions.WrongCredentials;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.Patient;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public Patient signUp(PatientSignupDTO patientSignupDTO){
        Patient patient = new Patient();
        patient.setName(patientSignupDTO.getName());
        patient.setGender(patientSignupDTO.getGender());
        patient.setEmail(patientSignupDTO.getEmail());
        patient.setAddress(patientSignupDTO.getAddress());
        patient.setAadharNumber(patientSignupDTO.getAadharNumber());
        patient.setPhoneNumber(patientSignupDTO.getPhoneNumber());
        patient.setVaccinationPreference(patientSignupDTO.getVaccinationPreference().toString());
        patient.setPassword(patientSignupDTO.getPassword());
        return patientRepository.save(patient);

    }

    public Patient login(PatientLoginDTO patientLoginDTO){
        Patient patient = patientRepository.getPatientByEmail(patientLoginDTO.getEmail());
        if(patient == null){
            throw new PatientDoesNotExistException("Patient Email Id is not Registered");
        }

        if(!patient.getPassword().equals(patientLoginDTO.getPassword())){
            throw new WrongCredentials("Incorrect Password");
        }
        return patient;

    }

    public void createAppointment(String email, String vaccinationCenterPreference){
        //get patient by email;
        Patient p = patientRepository.getPatientByEmail(email);
        //Identify patient vaccination preference
        String vPreference = p.getVaccinationPreference();
        if(vPreference.equals("Sputnik")){

        } else if (vPreference.equals("Covishield")) {

            
        }else{

        }
    }



}
