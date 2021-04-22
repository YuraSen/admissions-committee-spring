package com.senin.demo.repository;

import com.senin.demo.entity.AdmissionRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRequestRepository extends JpaRepository<AdmissionRequestEntity, Long> {
}
