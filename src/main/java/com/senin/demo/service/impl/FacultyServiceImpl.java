package com.senin.demo.service.impl;

import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.entity.FacultyEntity;
import com.senin.demo.exception.IncorrectIdRuntimeException;
import com.senin.demo.repository.FacultyRepository;
import com.senin.demo.service.FacultyService;
import com.senin.demo.service.mapper.FacultyMapper;
import com.senin.demo.util.UtilityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FacultyServiceImpl implements FacultyService {
    @PersistenceContext
    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;

    @Override
    public FacultyDTO findById(Long id) {
        UtilityService.isIdPositive(id);
        return facultyMapper.mapFacultyEntityToDTO(facultyRepository.findById(id)
                .orElseThrow(() -> new IncorrectIdRuntimeException(UtilityService.ID_CORRECT)));
    }

    @Override
    public Page<FacultyDTO> getAllFaculties(Pageable pageable) {
        Page<FacultyEntity> repositoryAll = facultyRepository.findAll(pageable);
        List<FacultyDTO> collect = repositoryAll.stream()
                .map(facultyMapper::mapFacultyEntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, countAllFaculty());
    }

    private Long countAllFaculty() {
        return facultyRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        UtilityService.isIdPositive(id);
        facultyRepository.deleteById(id);
    }

    @Override
    public void blockUnblockRegistration(FacultyDTO facultyDTO) {
        facultyRepository.blockUnblockRegistration(facultyDTO.getId(), facultyDTO.isAdmissionOpen());
    }

    @Override
    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        return facultyMapper.mapFacultyEntityToDTO(facultyRepository.save(facultyMapper.mapFacultyDTOToEntity(facultyDTO)));
    }

}
