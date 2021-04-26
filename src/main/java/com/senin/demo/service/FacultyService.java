package com.senin.demo.service;

import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.entity.FacultyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacultyService {
    FacultyDTO findById(Long id);

    Page<FacultyEntity> getAllFaculties(Pageable pageable);

    void deleteById(Long id);

    void blockUnblockRegistration(FacultyDTO facultyDTO);

    FacultyEntity createFaculty(FacultyDTO facultyDTO);
}
