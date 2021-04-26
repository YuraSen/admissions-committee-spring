package com.senin.demo.service;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.entity.AdmissionRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdmissionRequestService {

    AdmissionRequestDTO update(AdmissionRequestDTO admissionRequestDTO);

    Page<AdmissionRequestEntity> getAdmissionRequestsForFacultyById(Long id, Pageable pageable);

    Page<AdmissionRequestEntity> getAdmissionRequestsForUserByUsername(String username, Pageable pageable);

    AdmissionRequestDTO saveAdmissionRequest(AdmissionRequestDTO admissionRequestDTO);

}
