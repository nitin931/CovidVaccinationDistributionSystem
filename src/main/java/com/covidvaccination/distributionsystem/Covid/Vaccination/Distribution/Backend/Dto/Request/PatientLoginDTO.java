package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.Dto.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientLoginDTO {
    String email;
    String password;
}
