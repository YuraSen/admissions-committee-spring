package com.senin.demo.repository;

import com.senin.demo.entity.CandidateProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CandidateProfileRepository extends JpaRepository<CandidateProfile, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE CandidateProfile  cp SET " +
            "cp.firstName = :firstName," +
            "cp.lastName=:lastName," +
            "cp.email=:email," +
            "cp.address=:address," +
            "cp.city=:city," +
            "cp.region=:region," +
            "cp.school=:school," +
            "cp.phoneNumber=:phoneNumber, " +
            "cp.fileName=:fileName" +
            " WHERE cp.id = :id")
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
