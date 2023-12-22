package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.repository;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.Doctor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    @Query(value = "select * from doctor where vaccination_center_id=:vcid and patient_count = (select min(patient_count) from doctor where vaccination_center_id =:vcid)",nativeQuery = true)
    public List<Doctor> getMinDoctorByVC(UUID vcid);

    @Modifying
    @Transactional
    @Query(value = "update doctor set patient_count=:patientCount where id =:docId ",nativeQuery = true)
    public void updatePatientCountByOne(int patientCount, UUID docId);

    @Modifying
    @Transactional
    @Query(value = "insert into doctor_patient_list (doctor_id, patient_list_id) values (?2, ?1)", nativeQuery = true)
    public void insertIntoDocVsPatientTable(UUID patientId, UUID docId);

}
