package com.senin.demo.repository;

import com.senin.demo.dto.Role;
import com.senin.demo.dto.CandidateStatus;
import com.senin.demo.entity.Candidate;
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
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Candidate  c set c.role = :role," +
            "c.candidateStatus=:candidateStatus WHERE c.id = :id")
    int setCandidateUpdate(@Param("id") Long id,
                           @Param("role") Role role,
                           @Param("candidateStatus") CandidateStatus candidateStatus);


    Page<Candidate> findAll(Pageable pageable);


    Optional<Candidate> findByUsername(String username);
}
