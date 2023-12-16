package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.controller;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.VaccinationCenter;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vc")
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterService vaccinationCenterService;
    @PostMapping("/register")
    public VaccinationCenter register(@RequestBody VaccinationCenter vaccinationCenter){
        return vaccinationCenterService.registerVaccinationCenter(vaccinationCenter);
    }
}
