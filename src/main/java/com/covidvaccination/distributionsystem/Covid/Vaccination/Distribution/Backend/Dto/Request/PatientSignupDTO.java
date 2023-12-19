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
    long phoneNumber;
    String gender;
    String address;
    VaccinationPreference vaccinationPreference; // sputnik,covaxin,covishield

}
