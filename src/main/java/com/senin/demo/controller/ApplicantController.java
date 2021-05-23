package com.senin.demo.controller;


import com.senin.demo.dto.ApplicantDTO;
import com.senin.demo.dto.ApplicantProfileDTO;
import com.senin.demo.entity.Applicant;
import com.senin.demo.service.ApplicantService;
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

import static com.senin.demo.controller.ControllerAttributeConstant.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ApplicantController {

    @Value("${upload.path}")
    private String uploadPath;
    private final ApplicantService applicantService;
    private static final List<String> contentTypes = Arrays.asList(PNG, JPEG, GIF);

    @GetMapping("/api/applicant/registration")
    public String registrationRedirect() {
        return "/registration";
    }

    @PostMapping("/api/applicant/registration")
    public String createApplicant(@RequestParam("file") MultipartFile file,
                                  @Valid ApplicantDTO applicantDTO,
                                  Errors errors,
                                  @Valid ApplicantProfileDTO applicantProfileDTO,
                                  Errors errorsProfile, Model model) throws IOException {

        if (errors.hasErrors() || errorsProfile.hasErrors()) {
            model.mergeAttributes(ValidationErrorUtils.getErrorsMap(errors));
            model.mergeAttributes(ValidationErrorUtils.getErrorsMap(errorsProfile));
            model.addAttribute(APPLICANT_DTO, applicantDTO);
            model.addAttribute(APPLICANT_PROFILE_DTO, applicantProfileDTO);

            return "/registration";
        }
        if (contentTypes.contains(file.getContentType())) {
            applicantProfileDTO.setFileName(applicantService.saveFile(file, uploadPath));
        } else {
            model.addAttribute(ERROR_MESSAGE, ERROR_FILE_FORMAT);
            return "/applicant/request_form";
        }
        applicantService.createApplicant(applicantDTO, applicantProfileDTO);
        log.info("new user " + applicantDTO.getUsername() + " created!");
        return "redirect:/auth/login";
    }

    @DeleteMapping("/api/applicant/{id}")
    public void deleteById(@PathVariable Long id) {
        applicantService.deleteById(id);
    }

    @GetMapping("/api/applicant/profile")
    public String getApplicantProfile(@AuthenticationPrincipal User currentUser
            , Model model) {
        model.addAttribute(APPLICANT, applicantService.getByUsername(currentUser.getUsername()));
        return "/applicant/applicant_profile";
    }

    @GetMapping("/api/applicant/edit/{id}")
    public String getById(@PathVariable Long id, Model model) {
        Applicant applicant = applicantService.getById(id);
        model.addAttribute(APPLICANT, applicant);
        model.addAttribute(UPLOAD_PATH, uploadPath);
        return "/applicant/applicant_profile_edit";

    }

    @GetMapping("/api/applicant/update")
    public String updateApplicantForm(@AuthenticationPrincipal User currentUser, Model model) {
        model.addAttribute(APPLICANT, applicantService.getByUsername(currentUser.getUsername()));
        return "applicant/applicant_profile_edit";
    }

    @PostMapping("/api/applicant/update")
    public String updateApplicant(@RequestParam("file") MultipartFile file,
                                  @AuthenticationPrincipal User currentUser,
                                  @Valid ApplicantProfileDTO applicantProfileDTO,
                                  Errors errorsProfile, Model model) throws IOException {

        if (errorsProfile.hasErrors()) {
            model.mergeAttributes(ValidationErrorUtils.getErrorsMap(errorsProfile));

            model.addAttribute(USERNAME, currentUser.getUsername());
            model.addAttribute(APPLICANT_PROFILE_DTO, applicantProfileDTO);

            return "applicant/applicant_profile_edit";
        }
        if (!file.isEmpty()) {
            if (contentTypes.contains(file.getContentType())) {
                applicantProfileDTO.setFileName(applicantService.saveFile(file, uploadPath));
            } else {
                model.addAttribute(ERROR_MESSAGE, ERROR_FILE_FORMAT + " ");
                return "applicant/applicant_profile_edit";
            }
        }else{
            applicantProfileDTO.setFileName(applicantService.getByUsername(currentUser.getUsername()).getApplicantProfile().getFileName());
        }
        applicantService.updateApplicantProfile(applicantProfileDTO);
        return "redirect:/api/applicant/profile";
    }

    @GetMapping("/admin/applicant")
    public String getAllApplicant(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
            Model model) {
        Page<Applicant> page = applicantService.getAllApplicant(pageable);
        model.addAttribute(PAGE, page);
        model.addAttribute(URL, "/admin/applicant");

        return "/admin/applicant";
    }

    @GetMapping("/admin/applicant/edit/{id}")
    public String getApplicantById(@PathVariable Long id, Model model) {

        model.addAttribute(APPLICANT, applicantService.getById(id));
        return "/admin/applicant-edit";

    }

    @PostMapping("/admin/applicant/edit/{id}")
    public String updateApplicant(ApplicantDTO applicantDTO) {

        applicantService.updateApplicant(applicantDTO);
        return "redirect:/admin/applicant";

    }

    @PostMapping("/admin/applicant/delete/{id}")
    public String deleteRequest(@PathVariable(name = "id") Long id) {
        applicantService.deleteById(id);
        return "redirect:/admin/applicant";
    }
}