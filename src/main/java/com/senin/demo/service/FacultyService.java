package com.senin.demo.service;


import java.util.Objects;

import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.entity.Faculty;
import com.senin.demo.exception.FacultyNotFoundException;
import com.senin.demo.repository.FacultyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public Page<Faculty> getAllFaculties(Pageable pageable) {
        return facultyRepository.findAll(pageable);
    }

    public Faculty getById(Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found! by id: " + id));
    }

    public void deleteById(Long id) {
        facultyRepository.deleteById(id);
        log.info("Faculty removed id: " + id);
    }

    public void blockUnblockRegistration(FacultyDTO facultyDTO) {

        facultyRepository.blockUnblockRegistration(facultyDTO.getId(), facultyDTO.isAdmissionOpen());
    }

    public Faculty createFaculty(FacultyDTO facultyDTO) {
        return facultyRepository.save(
                Faculty.builder()
                        .id(facultyDTO.getId())
                        .nameEn(facultyDTO.getNameEn())
                        .nameUk(facultyDTO.getNameUk())
                        .descriptionEn(facultyDTO.getDescriptionEn())
                        .descriptionUk(facultyDTO.getDescriptionUk())
                        .budgetCapacity(facultyDTO.getBudgetCapacity())
                        .totalCapacity(facultyDTO.getTotalCapacity())
                        .requiredSubject1En(facultyDTO.getRequiredSubject1En())
                        .requiredSubject1Uk(facultyDTO.getRequiredSubject1Uk())
                        .requiredSubject2En(facultyDTO.getRequiredSubject2En())
                        .requiredSubject2Uk(facultyDTO.getRequiredSubject2Uk())
                        .requiredSubject3En(facultyDTO.getRequiredSubject3En())
                        .requiredSubject3Uk(facultyDTO.getRequiredSubject3Uk())
                        .admissionOpen(Objects.isNull(facultyDTO.getId()) || facultyDTO.isAdmissionOpen())
                        .build());
    }
}