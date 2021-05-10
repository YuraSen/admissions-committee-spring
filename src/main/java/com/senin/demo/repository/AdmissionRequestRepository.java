package com.senin.demo.repository;

import com.senin.demo.dto.AdmissionRequestStatus;
import com.senin.demo.entity.AdmissionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AdmissionRequestRepository extends JpaRepository<AdmissionRequest, Long> {

    Page<AdmissionRequest> findAllByCandidate_Username(String username, Pageable pageable);

    Page<AdmissionRequest> findAllByFaculty_Id(Long id, Pageable pageable);


    @Transactional
    @Modifying
    @Query("UPDATE AdmissionRequest  ar SET " +
            "ar.admissionRequestStatus = :admissionRequestStatus" +
            " WHERE ar.id = :id")
    int updateRequest(@Param("id") Long id,
                      @Param("admissionRequestStatus") AdmissionRequestStatus admissionRequestStatus);
}
