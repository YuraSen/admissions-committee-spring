package com.senin.demo.service;

import com.senin.demo.dto.FacultyDTO;

import java.util.List;

public interface FacultyService {
    FacultyDTO save(FacultyDTO facultyDTO);

    FacultyDTO findById(Long id);

    List<FacultyDTO> findAll();

    FacultyDTO update(FacultyDTO facultyDTO);

    void deleteById(Long id);
}
