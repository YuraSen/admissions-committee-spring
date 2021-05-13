package com.senin.demo.repository;

import com.senin.demo.entity.ApplicantProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ApplicantProfileRepository extends JpaRepository<ApplicantProfile, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE ApplicantProfile ap SET " +
            "ap.firstName = :firstName," +
            "ap.lastName=:lastName," +
            "ap.email=:email," +
            "ap.address=:address," +
            "ap.city=:city," +
            "ap.region=:region," +
            "ap.school=:school," +
            "ap.phoneNumber=:phoneNumber, " +
            "ap.fileName=:fileName" +
            " WHERE ap.id = :id")
    int setProfileUpdate(@Param("id") Long id,
                         @Param("firstName") String firstName,
                         @Param("lastName") String lastName,
                         @Param("email") String email,
                         @Param("address") String address,
                         @Param("city") String city,
                         @Param("region") String region,
                         @Param("school") String school,
                         @Param("phoneNumber") String phoneNumber,
                         @Param("fileName") String fileName);
}
