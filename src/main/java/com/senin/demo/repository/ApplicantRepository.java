package com.senin.demo.repository;

import com.senin.demo.dto.Role;
import com.senin.demo.dto.ApplicantStatus;
import com.senin.demo.entity.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Applicant a set a.role = :role," +
            "a.applicantStatus=:applicantStatus WHERE a.id = :id")
    int setApplicantUpdate(@Param("id") Long id,
                           @Param("role") Role role,
                           @Param("applicantStatus") ApplicantStatus applicantStatus);


    Page<Applicant> findAll(Pageable pageable);


    Optional<Applicant> findByUsername(String username);
}
