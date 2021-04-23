package com.senin.demo.service.impl;

import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.entity.FacultyEntity;
import com.senin.demo.exception.IncorrectIdRuntimeException;
import com.senin.demo.repository.FacultyRepository;
import com.senin.demo.service.FacultyService;
import com.senin.demo.util.UtilityService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FacultyImpl implements FacultyService {
    @PersistenceContext
    private final FacultyRepository facultyRepository;
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    public FacultyDTO mapFacultyEntityToDTO(FacultyEntity facultyEntity){
        return modelMapper.map(facultyEntity, FacultyDTO.class);
    }

    public FacultyEntity mapFacultyDtoToEntity(FacultyDTO facultyDTO){
        return modelMapper.map(facultyDTO, FacultyEntity.class);
    }

    @Override
    public FacultyDTO save(FacultyDTO facultyDTO) {
        return mapFacultyEntityToDTO(facultyRepository.save(mapFacultyDtoToEntity(facultyDTO)));
    }

    @Override
    public FacultyDTO findById(Long id) {
        UtilityService.isIdPositive(id);
        return mapFacultyEntityToDTO(facultyRepository.findById(id)
                .orElseThrow(() -> new IncorrectIdRuntimeException(UtilityService.ID_CORRECT)));
    }

    @Override
    public List<FacultyDTO> findAll() {
        return facultyRepository.findAll().stream().map(this::mapFacultyEntityToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FacultyDTO update(FacultyDTO facultyDTO) {
        return mapFacultyEntityToDTO(entityManager.merge(mapFacultyDtoToEntity(facultyDTO)));
    }

    @Override
    public void deleteById(Long id) {
        UtilityService.isIdPositive(id);
        facultyRepository.deleteById(id);
    }
}
