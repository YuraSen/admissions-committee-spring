package com.senin.demo.service;

import com.senin.demo.dto.*;
import com.senin.demo.entity.AdmissionRequest;
import com.senin.demo.entity.Applicant;
import com.senin.demo.entity.ApplicantProfile;
import com.senin.demo.exception.ApplicantAlreadyExistsException;
import com.senin.demo.exception.ApplicantNotFoundException;
import com.senin.demo.repository.ApplicantProfileRepository;
import com.senin.demo.repository.ApplicantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ApplicantService {
    private final ApplicantProfileRepository applicantProfileRepository;
    private final ApplicantRepository applicantRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public Page<Applicant> getAllApplicant(Pageable pageable) {
        return applicantRepository.findAll(pageable);
    }

    public Applicant getById(Long id) {
        return
                applicantRepository.findById(id)
                        .orElseThrow(
                                () -> new ApplicantNotFoundException("Applicant with id= " + id + " not found"));
    }

    public Applicant getByUsername(String name) {
        return applicantRepository.findByUsername(name)
                .orElseThrow(
                        () -> new ApplicantNotFoundException("Applicant with name: " + name + " not found"));
    }

    public Applicant createApplicant(ApplicantDTO applicantDTO, ApplicantProfileDTO applicantProfileDTO) {

        Applicant applicant = Applicant.builder()
                .username(applicantDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(applicantDTO.getPassword()))
                .role(Role.USER)
                .applicantStatus(ApplicantStatus.ACTIVE)
                .build();

        ApplicantProfile applicantProfile = ApplicantProfile.builder()
                .firstName(applicantProfileDTO.getFirstName())
                .lastName(applicantProfileDTO.getLastName())
                .email(applicantProfileDTO.getEmail())
                .address(applicantProfileDTO.getAddress())
                .city(applicantProfileDTO.getCity())
                .region(applicantProfileDTO.getRegion())
                .school(applicantProfileDTO.getSchool())
                .phoneNumber(applicantProfileDTO.getPhoneNumber())
                .fileName(applicantProfileDTO.getFileName())
                .applicant(applicant)
                .build();
        applicant.setApplicantProfile(applicantProfile);
        try {
            applicant = applicantRepository.save(applicant);
        } catch (DataIntegrityViolationException ex) {
            throw new ApplicantAlreadyExistsException("Already Exists!");
        }
        return applicant;
    }

    public Integer updateApplicant(ApplicantDTO applicantDTO) {
        setApplicantRequestsStatus(applicantDTO);
        return applicantRepository.setApplicantUpdate(applicantDTO.getId(), applicantDTO.getRole(), applicantDTO.getApplicantStatus());
    }

    public void setApplicantRequestsStatus(ApplicantDTO applicantDTO) {
        Applicant applicant = applicantRepository.findById(applicantDTO.getId()).orElseThrow();
        if (applicantDTO.getApplicantStatus() == ApplicantStatus.BLOCKED) {
            for (AdmissionRequest ar : applicant.getAdmissionRequestList()) {
                ar.setAdmissionRequestStatus(AdmissionRequestStatus.REJECTED);
            }
        }
        if (applicantDTO.getApplicantStatus() == ApplicantStatus.ACTIVE && applicant.getApplicantStatus() == ApplicantStatus.BLOCKED) {
            for (AdmissionRequest ar : applicant.getAdmissionRequestList()) {
                ar.setAdmissionRequestStatus(AdmissionRequestStatus.NEW);
            }
        }

    }

    public void deleteById(Long id) {
        log.info("Applicant removed id: " + id);
        applicantRepository.deleteById(id);
    }

    public Integer updateApplicantProfile(ApplicantProfileDTO applicantProfileDTO) {
        return applicantProfileRepository.setProfileUpdate(
                applicantProfileDTO.getId(),
                applicantProfileDTO.getFirstName(),
                applicantProfileDTO.getLastName(),
                applicantProfileDTO.getEmail(),
                applicantProfileDTO.getAddress(),
                applicantProfileDTO.getCity(),
                applicantProfileDTO.getRegion(),
                applicantProfileDTO.getSchool(),
                applicantProfileDTO.getPhoneNumber(),
                applicantProfileDTO.getFileName());
    }

    public String saveFile(MultipartFile file, String uploadPath) throws IOException {
        String resultFilename = null;
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
        }
        return resultFilename;
    }
}

