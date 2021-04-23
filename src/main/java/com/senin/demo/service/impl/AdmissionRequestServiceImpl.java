package com.senin.demo.service.impl;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.entity.AdmissionRequestEntity;
import com.senin.demo.entity.FacultyEntity;
import com.senin.demo.exception.IncorrectIdRuntimeException;
import com.senin.demo.repository.AdmissionRequestRepository;
import com.senin.demo.repository.FacultyRepository;
import com.senin.demo.service.AdmissionRequestService;
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
public class AdmissionRequestServiceImpl implements AdmissionRequestService {
    @PersistenceContext
    private final AdmissionRequestRepository admissionRequestRepository;
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    public AdmissionRequestDTO mapAdmissionRequestEntityToDTO(AdmissionRequestEntity admissionRequestEntity){
        return modelMapper.map(admissionRequestEntity, AdmissionRequestDTO.class);
    }

    public AdmissionRequestEntity mapAdmissionRequestDtoToEntity(AdmissionRequestDTO admissionRequestDTO){
        return modelMapper.map(admissionRequestDTO, AdmissionRequestEntity.class);
    }

    @Override
    public AdmissionRequestDTO save(AdmissionRequestDTO admissionRequestDTO) {
        return mapAdmissionRequestEntityToDTO(admissionRequestRepository.save(mapAdmissionRequestDtoToEntity(admissionRequestDTO)));
    }

    @Override
    public AdmissionRequestDTO findById(Long id) {
        UtilityService.isIdPositive(id);
        return mapAdmissionRequestEntityToDTO(admissionRequestRepository.findById(id)
                .orElseThrow(() -> new IncorrectIdRuntimeException(UtilityService.ID_CORRECT)));
    }

    @Override
    public List<AdmissionRequestDTO> findAll() {
        return admissionRequestRepository.findAll().stream().map(this::mapAdmissionRequestEntityToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AdmissionRequestDTO update(AdmissionRequestDTO user) {
        return mapAdmissionRequestEntityToDTO(entityManager.merge(mapAdmissionRequestDtoToEntity(user)));
    }

    @Override
    public void deleteById(Long id) {
        UtilityService.isIdPositive(id);
        admissionRequestRepository.deleteById(id);
    }
}
