package com.senin.demo.service.impl;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.entity.AdmissionRequestEntity;
import com.senin.demo.exception.RequestAlreadyExistsException;
import com.senin.demo.repository.AdmissionRequestRepository;
import com.senin.demo.service.AdmissionRequestService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdmissionRequestServiceImpl implements AdmissionRequestService {
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
    public Page<AdmissionRequestEntity> getAdmissionRequestsForFacultyById(Long id, Pageable pageable) {
        return mapAdmissionRequestEntityToDTO(admissionRequestRepository
                .findAllByFacultyId(mapAdmissionRequestDtoToEntity(id, pageable)));

    }

    @Override
    public Page<AdmissionRequestEntity> getAdmissionRequestsForUserByUsername(String username, Pageable pageable) {
        return mapAdmissionRequestEntityToDTO(admissionRequestRepository
                .findAllByUsername(mapAdmissionRequestDtoToEntity(username, pageable)));
    }

    @Override
    public AdmissionRequestDTO saveAdmissionRequest(AdmissionRequestDTO admissionRequestDTO) {
        try {
            return mapAdmissionRequestEntityToDTO(admissionRequestRepository.save(mapAdmissionRequestDtoToEntity(admissionRequestDTO)));
        } catch (DataIntegrityViolationException ex) {
            throw new RequestAlreadyExistsException("Request Already Exists!");
        }
    }
}
