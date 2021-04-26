package com.senin.demo.repository;

import com.senin.demo.entity.FacultyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface FacultyRepository extends JpaRepository<FacultyEntity, Long> {

    Page<FacultyEntity> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE FacultyEntity f set f.admissionOpen = :admissionOpen WHERE f.id = :id")
    void blockUnblockRegistration(@Param("id") Long id,
                                  @Param("admissionOpen") boolean admissionOpen);
}
