package com.senin.demo.service;

import com.senin.demo.dto.AdmissionRequestDTO;

import com.senin.demo.dto.AdmissionRequestStatus;
import com.senin.demo.entity.AdmissionRequest;
import com.senin.demo.entity.Applicant;
import com.senin.demo.entity.Faculty;
import com.senin.demo.exception.RequestAlreadyExistsException;
import com.senin.demo.exception.RequestNotFoundException;
import com.senin.demo.repository.AdmissionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AdmissionRequestService {
    @Value("${upload.path}")
    private String uploadPath;
    private final AdmissionRequestRepository admissionRequestRepository;
    private final ApplicantService applicantService;
    private final FacultyService facultyService;

    public void updateStatusOfRequest(AdmissionRequestDTO admissionRequestDTO) {
        admissionRequestRepository.updateRequest(admissionRequestDTO.getId(), admissionRequestDTO.getAdmissionRequestStatus());
    }

    public Page<AdmissionRequest> getAdmissionRequestsForFacultyWithId(Long id, Pageable pageable) {
        return admissionRequestRepository
                .findAllByFaculty_Id(id, pageable);
    }

    public Page<AdmissionRequest> getAdmissionRequestsForUserWithUsername(String username, Pageable pageable) {
        return admissionRequestRepository
                .findAllByApplicant_Username(username, pageable);
    }

    public AdmissionRequest saveAdmissionRequest(AdmissionRequestDTO admissionRequestDTO) {
        try {
            Applicant applicant = Applicant.builder().id(admissionRequestDTO.getApplicantId()).build();
            Faculty faculty = Faculty.builder().id(admissionRequestDTO.getFacultyId()).build();

            return admissionRequestRepository.save(
                    AdmissionRequest.builder()
                            .id(admissionRequestDTO.getId())
                            .admissionRequestStatus(AdmissionRequestStatus.NEW)
                            .applicant(applicant)
                            .faculty(faculty)
                            .requiredSubject1Grade(admissionRequestDTO.getRequiredSubject1Grade())
                            .requiredSubject2Grade(admissionRequestDTO.getRequiredSubject2Grade())
                            .requiredSubject3Grade(admissionRequestDTO.getRequiredSubject3Grade())
                            .build());
        } catch (DataIntegrityViolationException ex) {
            throw new RequestAlreadyExistsException("Request Already Exists!");
        }
    }

    @Transactional
    public void deleteRequest(Long id) {
        admissionRequestRepository.deleteById(id);
    }

    public AdmissionRequest getById(Long id) {
        return admissionRequestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Request by id= " + id + "not found"));
    }

    public AdmissionRequestDTO getAdmissionRequestDTO(Long facultyId, String username) {
        Applicant applicant = applicantService.getByUsername(username);
        Faculty faculty = facultyService.getById(facultyId);
        return AdmissionRequestDTO.builder().applicant(applicant).faculty(faculty).build();
    }
}
