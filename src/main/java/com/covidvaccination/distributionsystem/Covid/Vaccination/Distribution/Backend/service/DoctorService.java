package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.service;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.Doctor;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.VaccinationCenter;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    //Doctor will get assigned to vaccination center having minimum number of doctors
    public Doctor registerDoctor(Doctor obj){
        List<VaccinationCenter> vaccinationCenterList = vaccinationCenterService.getMinDocListVC();
        VaccinationCenter vaccinationCenter = vaccinationCenterList.get(0);
        obj.setVaccinationCenter(vaccinationCenter);
        vaccinationCenterService.updateDocCountByOne(vaccinationCenter);
        return doctorRepository.save(obj);
    }

    public List<Doctor> getMinDoctorByVC(UUID vcid){
        return doctorRepository.getMinDoctorByVC(vcid);
    }

    public void updatePatientCountByOne(Doctor doctor){
        UUID id = doctor.getId();
        int patientCount = doctor.getPatientCount()+1;
        doctorRepository.updatePatientCountByOne(patientCount,id);
    }

    public void addPatientToDoctor(UUID patientId, UUID doctorId){
        doctorRepository.insertIntoDocVsPatientTable(patientId,doctorId);
    }

}
