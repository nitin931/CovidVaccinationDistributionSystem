package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Request;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Enums.VaccinationCenterPreference;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateAppointmentDTO {
    UUID id;
    VaccinationCenterPreference vaccinationCenterPreference;

}
