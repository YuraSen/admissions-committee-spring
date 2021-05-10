package com.senin.demo.controller;


import com.senin.demo.dto.CandidateDTO;
import com.senin.demo.dto.CandidateProfileDTO;
import com.senin.demo.entity.Candidate;
import com.senin.demo.service.CandidateService;
import com.senin.demo.util.ValidationErrorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CandidateController {
    @Value("${upload.path}")
    private String uploadPath;
    private final CandidateService candidateService;
    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

    @GetMapping("/api/candidate/registration")
    public String registrationRedirect() {
        return "/registration";
    }

    @PostMapping("/api/candidate/registration")
    public String createCandidate(@RequestParam("file") MultipartFile file,
                                  @Valid CandidateDTO candidateDTO,
                                  Errors errors,
                                  @Valid CandidateProfileDTO candidateProfileDTO,
                                  Errors errorsProfile, Model model) throws IOException {

        if (errors.hasErrors() || errorsProfile.hasErrors()) {
            model.mergeAttributes(ValidationErrorUtils.getErrorsMap(errors));
            model.mergeAttributes(ValidationErrorUtils.getErrorsMap(errorsProfile));
            model.addAttribute("candidateDTO", candidateDTO);
            model.addAttribute("candidateProfileDTO", candidateProfileDTO);

            return "/registration";
        }
        if (contentTypes.contains(file.getContentType())) {
            candidateProfileDTO.setFileName(candidateService.saveFile(file, uploadPath));
        } else {
            model.addAttribute("errorMessage", "Wrong file format. Required: image/png, image/jpeg, image/gif ");
            return "/candidate/request_form";
        }
        candidateService.createCandidate(candidateDTO, candidateProfileDTO);
        log.info("new user " + candidateDTO.getUsername() + " created!");
        return "redirect:/auth/login";
    }

    @DeleteMapping("/api/candidate/{id}")
    public void deleteById(@PathVariable Long id) {
        candidateService.deleteById(id);
    }

    @GetMapping("/api/candidate/profile")
    public String getCandidateProfile(@AuthenticationPrincipal User currentUser
            , Model model) {
        model.addAttribute("candidate", candidateService.getByUsername(currentUser.getUsername()));
        return "/candidate/candidate_profile";
    }

    @GetMapping("/api/candidate/edit/{id}")
    public String getById(@PathVariable Long id, Model model) {
        Candidate candidate = candidateService.getById(id);
        model.addAttribute("candidate", candidate);
        model.addAttribute("uploadPath", uploadPath);
        return "/candidate/candidate_profile_edit";

    }

    @GetMapping("/api/candidate/update")
    public String updateCandidateForm(@AuthenticationPrincipal User currentUser, Model model) {
        model.addAttribute("candidate", candidateService.getByUsername(currentUser.getUsername()));
        return "candidate/candidate_profile_edit";
    }

    @PostMapping("/api/candidate/update")
    public String updateCandidate(@RequestParam("file") MultipartFile file,
                                  @AuthenticationPrincipal User currentUser,
                                  @Valid CandidateProfileDTO candidateProfileDTO,
                                  Errors errorsProfile, Model model) throws IOException {

        if (errorsProfile.hasErrors()) {
            model.mergeAttributes(ValidationErrorUtils.getErrorsMap(errorsProfile));

            model.addAttribute("username", currentUser.getUsername());
            model.addAttribute("candidateProfileDTO", candidateProfileDTO);

            return "candidate/candidate_profile_edit";
        }
        if (!file.isEmpty()) {
            if (contentTypes.contains(file.getContentType())) {
                candidateProfileDTO.setFileName(candidateService.saveFile(file, uploadPath));
            } else {
                model.addAttribute("errorMessage", "Wrong file format. Required: image/png, image/jpeg, image/gif ");
                return "candidate/candidate_profile_edit";
            }
        }else{
            candidateProfileDTO.setFileName(candidateService.getByUsername(currentUser.getUsername()).getCandidateProfile().getFileName());
        }
        candidateService.updateCandidateProfile(candidateProfileDTO);
        return "redirect:/api/candidate/profile";
    }

    @GetMapping("/admin/candidate")
    public String getAllCandidates(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
            Model model) {
        Page<Candidate> page = candidateService.getAllCandidates(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/admin/candidate");

        return "/admin/candidates";
    }

    @GetMapping("/admin/candidate/edit/{id}")
    public String getCandidateById(@PathVariable Long id, Model model) {

        model.addAttribute("candidate", candidateService.getById(id));
        return "/admin/candidates-edit";

    }

    @PostMapping("/admin/candidate/edit/{id}")
    public String updateCandidate(CandidateDTO candidateDTO) {

        candidateService.updateCandidate(candidateDTO);
        return "redirect:/admin/candidate";

    }

    @PostMapping("/admin/candidate/delete/{id}")
    public String deleteRequest(@PathVariable(name = "id") Long id) {
        candidateService.deleteById(id);
        return "redirect:/admin/candidate";
    }
}