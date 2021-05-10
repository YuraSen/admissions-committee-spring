package com.senin.demo.repository;

import com.senin.demo.entity.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Page<Faculty> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Faculty f set f.admissionOpen = :admissionOpen WHERE f.id = :id")
    void blockUnblockRegistration(@Param("id") Long id,
                                  @Param("admissionOpen") boolean admissionOpen);
}
