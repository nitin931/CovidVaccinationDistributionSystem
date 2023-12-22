package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Response;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.Doctor;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.Patient;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.VaccinationCenter;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentDTO {
    int doseNumber;
    Patient patient;
    Doctor doctor;
    VaccinationCenter vaccinationCenter;
}
