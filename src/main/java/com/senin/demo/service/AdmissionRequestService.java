package com.senin.demo.service;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.dto.FacultyDTO;

import java.util.List;

public interface AdmissionRequestService {
    AdmissionRequestDTO save(AdmissionRequestDTO admissionRequestDTO);

    AdmissionRequestDTO findById(Long id);

    List<AdmissionRequestDTO> findAll();

    AdmissionRequestDTO update(AdmissionRequestDTO facultyDTO);

    void deleteById(Long id);
}
