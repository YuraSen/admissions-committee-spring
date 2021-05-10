package com.senin.demo.service;

import com.senin.demo.dto.*;
import com.senin.demo.entity.AdmissionRequest;
import com.senin.demo.entity.Candidate;
import com.senin.demo.entity.CandidateProfile;
import com.senin.demo.exception.CandidateAlreadyExistsException;
import com.senin.demo.exception.CandidateNotFoundException;
import com.senin.demo.repository.CandidateProfileRepository;
import com.senin.demo.repository.CandidateRepository;
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
public class CandidateService {
    private final CandidateProfileRepository candidateProfileRepository;
    private final CandidateRepository candidateRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public Page<Candidate> getAllCandidates(Pageable pageable) {
        return candidateRepository.findAll(pageable);
    }

    public Candidate getById(Long id) {
        return
                candidateRepository.findById(id)
                        .orElseThrow(
                                () -> new CandidateNotFoundException("Candidate with id= " + id + " not found"));
    }

    public Candidate getByUsername(String name) {
        return candidateRepository.findByUsername(name)
                .orElseThrow(
                        () -> new CandidateNotFoundException("Candidate with name: " + name + " not found"));
    }

    public Candidate createCandidate(CandidateDTO candidateDTO, CandidateProfileDTO candidateProfileDTO) {

        Candidate candidate = Candidate.builder()
                .username(candidateDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(candidateDTO.getPassword()))
                .role(Role.USER)
                .candidateStatus(CandidateStatus.ACTIVE)
                .build();

        CandidateProfile candidateProfile = CandidateProfile.builder()
                .firstName(candidateProfileDTO.getFirstName())
                .lastName(candidateProfileDTO.getLastName())
                .email(candidateProfileDTO.getEmail())
                .address(candidateProfileDTO.getAddress())
                .city(candidateProfileDTO.getCity())
                .region(candidateProfileDTO.getRegion())
                .school(candidateProfileDTO.getSchool())
                .phoneNumber(candidateProfileDTO.getPhoneNumber())
                .fileName(candidateProfileDTO.getFileName())
                .candidate(candidate)
                .build();
        candidate.setCandidateProfile(candidateProfile);
        try {
            candidate = candidateRepository.save(candidate);
        } catch (DataIntegrityViolationException ex) {
            throw new CandidateAlreadyExistsException("Already Exists!");
        }
        return candidate;
    }

    public Integer updateCandidate(CandidateDTO candidateDTO) {
        setCandidateRequestsStatus(candidateDTO);
        return candidateRepository.setCandidateUpdate(candidateDTO.getId(), candidateDTO.getRole(), candidateDTO.getCandidateStatus());
    }

    public void setCandidateRequestsStatus(CandidateDTO candidateDTO) {
        Candidate candidate = candidateRepository.findById(candidateDTO.getId()).orElseThrow();
        if (candidateDTO.getCandidateStatus() == CandidateStatus.BLOCKED) {
            for (AdmissionRequest ar : candidate.getAdmissionRequestList()) {
                ar.setAdmissionRequestStatus(AdmissionRequestStatus.REJECTED);
            }
        }
        if (candidateDTO.getCandidateStatus() == CandidateStatus.ACTIVE && candidate.getCandidateStatus() == CandidateStatus.BLOCKED) {
            for (AdmissionRequest ar : candidate.getAdmissionRequestList()) {
                ar.setAdmissionRequestStatus(AdmissionRequestStatus.NEW);
            }
        }

    }

    public void deleteById(Long id) {
        log.info("Candidate removed id: " + id);
        candidateRepository.deleteById(id);
    }

    public Integer updateCandidateProfile(CandidateProfileDTO candidateProfileDTO) {
        return candidateProfileRepository.setProfileUpdate(
                candidateProfileDTO.getId(),
                candidateProfileDTO.getFirstName(),
                candidateProfileDTO.getLastName(),
                candidateProfileDTO.getEmail(),
                candidateProfileDTO.getAddress(),
                candidateProfileDTO.getCity(),
                candidateProfileDTO.getRegion(),
                candidateProfileDTO.getSchool(),
                candidateProfileDTO.getPhoneNumber(),
                candidateProfileDTO.getFileName());
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

