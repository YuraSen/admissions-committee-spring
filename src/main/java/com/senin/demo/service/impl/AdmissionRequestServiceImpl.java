package com.senin.demo.service.impl;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.dto.UserDTO;
import com.senin.demo.entity.AdmissionRequestEntity;
import com.senin.demo.entity.UserEntity;
import com.senin.demo.exception.RequestAlreadyExistsException;
import com.senin.demo.repository.AdmissionRequestRepository;
import com.senin.demo.service.AdmissionRequestService;
import com.senin.demo.service.mapper.AdmissionRequestMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdmissionRequestServiceImpl implements AdmissionRequestService, AdmissionRequestMapper {
    @PersistenceContext
    private final AdmissionRequestRepository admissionRequestRepository;
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    public AdmissionRequestDTO mapAdmissionRequestEntityToDTO(AdmissionRequestEntity admissionRequestEntity) {
        return modelMapper
                .map(admissionRequestEntity, AdmissionRequestDTO.class);
    }

    public AdmissionRequestEntity mapAdmissionRequestDtoToEntity(AdmissionRequestDTO admissionRequestDTO) {
        return modelMapper
                .map(admissionRequestDTO, AdmissionRequestEntity.class);
    }


    @Override
    @Transactional
    public AdmissionRequestDTO update(AdmissionRequestDTO admissionRequestDTO) {
        return mapAdmissionRequestEntityToDTO(entityManager
                .merge(mapAdmissionRequestDtoToEntity(admissionRequestDTO)));
    }

    @Override
    public Page<AdmissionRequestDTO> getAdmissionRequestsForFacultyById(Long id, Pageable pageable) {
        Page<AdmissionRequestEntity> allByFacultyEntity_id = admissionRequestRepository.findAllByFacultyEntity_Id(id, pageable);
        List<AdmissionRequestDTO> collect = allByFacultyEntity_id.stream()
                .map(this::mapAdmissionRequestEntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, countAllAdmission());
    }

    @Override
    public Page<AdmissionRequestDTO> getAdmissionRequestsForUserByUsername(String username, Pageable pageable) {
        Page<AdmissionRequestEntity> allByUserEntity_username = admissionRequestRepository.findAllByUserEntity_Username(username, pageable);
        List<AdmissionRequestDTO> collect = allByUserEntity_username.stream()
                .map(this::mapAdmissionRequestEntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, countAllAdmission());
    }

    private Long countAllAdmission() {
        return admissionRequestRepository.count();
    }

    @Override
    public AdmissionRequestDTO saveAdmissionRequest(AdmissionRequestDTO admissionRequestDTO) {
        try {
            return mapAdmissionRequestEntityToDTO(admissionRequestRepository.save(mapAdmissionRequestDtoToEntity(admissionRequestDTO)));
        } catch (DataIntegrityViolationException ex) {
            throw new RequestAlreadyExistsException("Request Already Exists!");
        }
    }

    @Override
    public AdmissionRequestDTO AdmissionRequestEntityToDTO(AdmissionRequestEntity admissionRequestEntity) {
        if (admissionRequestEntity == null) {
            return null;
        }
        AdmissionRequestDTO admissionRequestDTO = new AdmissionRequestDTO();
        admissionRequestDTO.setId(admissionRequestEntity.getId());
        admissionRequestDTO.setUserEntity(admissionRequestEntity.getUserEntity());
        admissionRequestDTO.setFacultyEntity(admissionRequestEntity.getFacultyEntity());
        admissionRequestDTO.setFirstRequiredSubjectMark(admissionRequestEntity.getFirstRequiredSubjectMark());
        admissionRequestDTO.setSecondRequiredSubjectMark(admissionRequestEntity.getSecondRequiredSubjectMark());
        admissionRequestDTO.setThirdRequiredSubjectMark(admissionRequestEntity.getThirdRequiredSubjectMark());
        return admissionRequestDTO;
    }

    @Override
    public AdmissionRequestEntity AdmissionRequestDTOtoEntity(AdmissionRequestDTO admissionRequestDTO) {
        if (admissionRequestDTO == null) {
            return null;
        }
        AdmissionRequestEntity admissionRequestEntity = new AdmissionRequestEntity();
        admissionRequestEntity.setId(admissionRequestDTO.getId());
        admissionRequestEntity.setUserEntity(admissionRequestDTO.getUserEntity());
        admissionRequestEntity.setFacultyEntity(admissionRequestDTO.getFacultyEntity());
        admissionRequestEntity.setFirstRequiredSubjectMark(admissionRequestDTO.getFirstRequiredSubjectMark());
        admissionRequestEntity.setSecondRequiredSubjectMark(admissionRequestDTO.getSecondRequiredSubjectMark());
        admissionRequestEntity.setThirdRequiredSubjectMark(admissionRequestDTO.getThirdRequiredSubjectMark());
        return admissionRequestEntity;
    }
}
