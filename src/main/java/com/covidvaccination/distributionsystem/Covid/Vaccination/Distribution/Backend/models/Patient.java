package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String gender;
    private int doseCount;
    @Column(unique = true)
    private long aadharNumber;
    private String vaccinationPreference;

    private String address;
    @Column(unique = true )
    private long phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;

}
