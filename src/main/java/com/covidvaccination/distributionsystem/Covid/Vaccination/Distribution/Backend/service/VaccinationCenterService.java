package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.service;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.VaccinationCenter;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VaccinationCenterService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    public VaccinationCenter registerVaccinationCenter(VaccinationCenter vaccinationCenter){
        return vaccinationCenterRepository.save(vaccinationCenter);

    }
    public List<VaccinationCenter> getMinDocListVC(){
        return vaccinationCenterRepository.getMinDocVC();

    }
    public void updateDocCountByOne(VaccinationCenter vaccinationCenter){
        UUID id = vaccinationCenter.getId();
        int docCount = vaccinationCenter.getDoctorCount()+1;
        vaccinationCenterRepository.updateDocCountByOne(id,docCount);
    }


    public void updatePatientCountByOne(VaccinationCenter vaccinationCenter){
        UUID id = vaccinationCenter.getId();
        int patientCount = vaccinationCenter.getPatientsCount() + 1;
        vaccinationCenterRepository.updatePatientCountByOne(patientCount,id);
    }

    public List<VaccinationCenter> getMinVcByTypeNdPreference(String type, String preference){
        if(preference.equals("Sputnik")){
            return vaccinationCenterRepository.getAllVcOfSameTypeNdSputnikCount(type);
        } else if (preference.equals("Covishield")) {
            return vaccinationCenterRepository.getAllVcOfSameTypeNdCovishieldCount(type);
        }else {
            return vaccinationCenterRepository.getAllVcOfSameTypeNdCovaxinCount(type);
        }
    }

}
