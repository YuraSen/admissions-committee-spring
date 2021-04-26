package com.senin.demo.repository;

import com.senin.demo.dto.AdmissionRequestStatus;
import com.senin.demo.entity.AdmissionRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AdmissionRequestRepository extends JpaRepository<AdmissionRequestEntity, Long> {
    Page<AdmissionRequestEntity> findAllByUsername(String username, Pageable pageable);

    Page<AdmissionRequestEntity> findAllByFacultyId(Long id, Pageable pageable);


    @Transactional
    @Modifying
    @Query("UPDATE AdmissionRequestEntity  ar SET " +
            "ar.admissionRequestStatus = :admissionRequestStatus" +
            " WHERE ar.id = :id")
    int updateRequest(@Param("id") Long id,
                      @Param("admissionRequestStatus") AdmissionRequestStatus admissionRequestStatus);
}
