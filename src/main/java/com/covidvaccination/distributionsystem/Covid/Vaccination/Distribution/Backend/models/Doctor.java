package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String docDegree;
    @ManyToOne
    private VaccinationCenter vaccinationCenter;
    private int patientCount;
    @OneToMany
    private List<Patient> patientList;


}
