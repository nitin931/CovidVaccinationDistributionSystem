package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Request;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Enums.VaccinationPreference;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientSignupDTO {
    String name;
    String email;
    String password;
    String aadharNumber;
    String phoneNumber;
    String gender;
    VaccinationPreference vaccinationPreference; // sputnik,covaxin,covishield

}
