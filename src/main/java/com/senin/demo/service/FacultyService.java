package com.senin.demo.service;

import com.senin.demo.dto.FacultyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacultyService {
    FacultyDTO findById(Long id);

    Page<FacultyDTO> getAllFaculties(Pageable pageable);

    void deleteById(Long id);

    void blockUnblockRegistration(FacultyDTO facultyDTO);

    FacultyDTO createFaculty(FacultyDTO facultyDTO);
}
