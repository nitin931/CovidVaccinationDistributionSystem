package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.service;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Request.PatientLoginDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Request.PatientSignupDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Response.AppointmentDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Exceptions.PatientDoesNotExistException;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Exceptions.WrongCredentials;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.Doctor;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.Patient;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.VaccinationCenter;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.repository.PatientRepository;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    VaccinationCenterService vaccinationCenterService;
    @Autowired
    DoctorService doctorService;

    @Autowired
    MailService mailService;


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

    public AppointmentDTO createAppointment(String email, String vaccinationCenterPreference){
        //get patient by email;
        Patient p = patientRepository.getPatientByEmail(email);
        //Identify patient vaccination preference
        String vPreference = p.getVaccinationPreference();
        List<VaccinationCenter> vaccinationCenterList= vaccinationCenterService.getMinVcByTypeNdPreference(vaccinationCenterPreference,vPreference);
        //get first vaccination center from list
        VaccinationCenter patientsVc = vaccinationCenterList.get(0);
        //Assign doctor who is handling minimum number or patient
        List<Doctor> doctorList = doctorService.getMinDoctorByVC(patientsVc.getId());
        //Take out min doctor
        Doctor patientDoctor = doctorList.get(0);

        updateDoseCountByOne(p);
        vaccinationCenterService.updatePatientCountByOne(patientsVc);
        doctorService.updatePatientCountByOne(patientDoctor);
        patientDoctor.getPatientList().add(p);
        doctorService.addPatientToDoctor(p.getId(),patientDoctor.getId());
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDoseNumber(p.getDoseCount() + 1);
        appointmentDTO.setVaccinationCenter(patientsVc);
        appointmentDTO.setPatient(p);
        appointmentDTO.setDoctor(patientDoctor);

        String to = p.getEmail();
        String sub = String.format("Congrats !! %s you got vaccinated", p.getName());
        String text = String.format("Hi %s,\n" +
                        " your appointment got created. Below are your appointment details :\n" +
                "1. Dose count : %d\n" +
                "2. Doctor Name :%s\n" +
                "3. Vaccination Center Name : %s\n" +
                "4. Vaccination Center Address : %s",
                p.getName(),
                p.getDoseCount(),
                patientDoctor.getName(),
                patientsVc.getName(),
                patientsVc.getAddress()
                );
        mailService.generateMail(to,sub,text);
        return appointmentDTO;
    }

    public void updateDoseCountByOne(Patient patient){
        UUID id = patient.getId();
        int doseCount = patient.getDoseCount()+1;
        patientRepository.updateDoseCountByOne(id,doseCount);
    }



}
