package com.senin.demo.service.impl;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.entity.AdmissionRequestEntity;
import com.senin.demo.exception.RequestAlreadyExistsException;
import com.senin.demo.repository.AdmissionRequestRepository;
import com.senin.demo.service.AdmissionRequestService;
import com.senin.demo.service.mapper.AdmissionRequestMapper;
import lombok.AllArgsConstructor;
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
public class AdmissionRequestServiceImpl implements AdmissionRequestService {
    @PersistenceContext
    private final AdmissionRequestRepository admissionRequestRepository;
    private final EntityManager entityManager;
    private final AdmissionRequestMapper admissionRequestMapper;

    @Override
    @Transactional
    public AdmissionRequestDTO update(AdmissionRequestDTO admissionRequestDTO) {
        return admissionRequestMapper.mapAdmissionRequestEntityToDTO(entityManager
                .merge(admissionRequestMapper.mapAdmissionRequestDTOtoEntity(admissionRequestDTO)));
    }

    @Override
    public Page<AdmissionRequestDTO> getAdmissionRequestsForFacultyById(Long id, Pageable pageable) {
        Page<AdmissionRequestEntity> allByFacultyEntity_id = admissionRequestRepository.findAllByFacultyEntity_Id(id, pageable);
        List<AdmissionRequestDTO> collect = allByFacultyEntity_id.stream()
                .map(admissionRequestMapper::mapAdmissionRequestEntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, countAllAdmission());
    }

    @Override
    public Page<AdmissionRequestDTO> getAdmissionRequestsForUserByUsername(String username, Pageable pageable) {
        Page<AdmissionRequestEntity> allByUserEntity_username = admissionRequestRepository.findAllByUserEntity_Username(username, pageable);
        List<AdmissionRequestDTO> collect = allByUserEntity_username.stream()
                .map(admissionRequestMapper::mapAdmissionRequestEntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, countAllAdmission());
    }

    private Long countAllAdmission() {
        return admissionRequestRepository.count();
    }

    @Override
    public AdmissionRequestDTO saveAdmissionRequest(AdmissionRequestDTO admissionRequestDTO) {
        try {
            return admissionRequestMapper.mapAdmissionRequestEntityToDTO(admissionRequestRepository.save(admissionRequestMapper.mapAdmissionRequestDTOtoEntity(admissionRequestDTO)));
        } catch (DataIntegrityViolationException ex) {
            throw new RequestAlreadyExistsException("Request Already Exists!");
        }
    }

}
