package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.controller;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Request.PatientLoginDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Request.PatientSignupDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Response.AppointmentDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Response.GenralMessageDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Enums.VaccinationCenterPreference;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Enums.VaccinationPreference;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Exceptions.PatientDoesNotExistException;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Exceptions.WrongCredentials;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.Patient;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody PatientSignupDTO patientSignupDTO){
        Patient patient = patientService.signUp(patientSignupDTO);
        return new ResponseEntity(patient, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody PatientLoginDTO patientLoginDTO){
        try {
            Patient patient = patientService.login(patientLoginDTO);
            return new ResponseEntity(patient,HttpStatus.OK);
        }catch (WrongCredentials wrongCredentials){
            return new ResponseEntity(new GenralMessageDTO(wrongCredentials.getMessage()),HttpStatus.UNAUTHORIZED);

        }catch (PatientDoesNotExistException patientDoesNotExistException){
            return new ResponseEntity(new GenralMessageDTO(patientDoesNotExistException.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/createAppointment")
    public ResponseEntity createAppointment(@RequestParam String email, @RequestParam VaccinationCenterPreference vaccinationCenterPreference){
        AppointmentDTO appointmentDTO = patientService.createAppointment(email,vaccinationCenterPreference.toString());
        return new ResponseEntity(appointmentDTO, HttpStatus.OK);
    }

}
