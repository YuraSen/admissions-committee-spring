package com.senin.demo.service;

import com.senin.demo.dto.AdmissionRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdmissionRequestService {

    AdmissionRequestDTO update(AdmissionRequestDTO admissionRequestDTO);

    Page<AdmissionRequestDTO> getAdmissionRequestsForFacultyById(Long id, Pageable pageable);

    Page<AdmissionRequestDTO> getAdmissionRequestsForUserByUsername(String username, Pageable pageable);

    AdmissionRequestDTO saveAdmissionRequest(AdmissionRequestDTO admissionRequestDTO);

}
