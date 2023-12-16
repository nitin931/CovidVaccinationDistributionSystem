package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.repository;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.Backend.models.VaccinationCenter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, UUID> {

    @Query(value = "SELECT * FROM `vaccination_center` WHERE doctor_count = (select min(doctor_count) from vaccination_center)",nativeQuery = true)
    public List<VaccinationCenter> getMinDocVC();

    @Modifying
    @Transactional
    @Query(value = "update vaccination_center set doctor_count=:docCount where id =:id",nativeQuery = true)
    public void updateDocCountByOne(UUID id,int docCount);

}
