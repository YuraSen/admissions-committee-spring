package com.senin.demo.repository;

import com.senin.demo.entity.CandidateProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfileEntity, Long> {

}
