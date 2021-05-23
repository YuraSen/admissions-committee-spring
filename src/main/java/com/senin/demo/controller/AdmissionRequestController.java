package com.senin.demo.controller;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.entity.AdmissionRequest;
import com.senin.demo.exception.RequestAlreadyExistsException;
import com.senin.demo.service.AdmissionRequestService;
import com.senin.demo.util.ValidationErrorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import javax.validation.Valid;

import static com.senin.demo.controller.ControllerAttributeConstant.*;

@Slf4j
@Controller
@AllArgsConstructor
public class AdmissionRequestController{

    private final AdmissionRequestService admissionRequestService;

    @GetMapping("/applicant/submit_request_form")
    public String getRequestForm(FacultyDTO facultyDTO,
                                 @AuthenticationPrincipal User currentUser, Model model) {

        if (facultyDTO.isAdmissionOpen()) {
            AdmissionRequestDTO admissionRequestDTO = admissionRequestService.getAdmissionRequestDTO(facultyDTO.getId(), currentUser.getUsername());
            model.addAttribute(FACULTY, admissionRequestDTO.getFaculty());
            model.addAttribute(APPLICANT, admissionRequestDTO.getApplicant());
            return "/applicant/request_form";
        }
        return "/applicant/applicant_requests";
    }

    @PostMapping("/applicant/submit_request")
    public String createRequestFromApplicant(
            @Valid AdmissionRequestDTO admissionRequestDTO,
            Errors errors, @AuthenticationPrincipal User currentUser,
            Model model) {
        AdmissionRequestDTO admissionRequest = admissionRequestService
                .getAdmissionRequestDTO(admissionRequestDTO.getFacultyId(), currentUser.getUsername());
        model.addAttribute(FACULTY_ID, admissionRequest.getFaculty().getId());
        model.addAttribute(FACULTY, admissionRequest.getFaculty());
        model.addAttribute(APPLICANT, admissionRequest.getApplicant());
        if (errors.hasErrors()) {
            model.mergeAttributes(ValidationErrorUtils.getErrorsMap(errors));
            return "/applicant/request_form";
        }
        try {
            admissionRequestService.saveAdmissionRequest(admissionRequestDTO);
        } catch (RequestAlreadyExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/applicant/request_form";
        }
        return "redirect:/applicant/applicant_requests";
    }

    @GetMapping("/applicant/applicant_requests")
    public String getAllUserRequests(@PageableDefault(sort = {"id"},
            direction = Sort.Direction.ASC, size = 5) Pageable pageable,
                                     @AuthenticationPrincipal User currentUser
            , Model model) {

        Page<AdmissionRequest> page = admissionRequestService.getAdmissionRequestsForUserWithUsername(currentUser.getUsername(), pageable);
        model.addAttribute(PAGE, page);
        model.addAttribute(URL, "/applicant/applicant_requests");
        model.addAttribute(USERNAME, currentUser.getUsername());
        model.addAttribute(REQUESTS_LIST, page);
        return "/applicant/applicant_requests";
    }

    @PostMapping("/applicant/delete_request/{id}")
    public String deleteRequest(@PathVariable(name = ID) Long id) {
        admissionRequestService.deleteRequest(id);
        return "redirect:/applicant/applicant_requests";
    }

    @GetMapping("/admin/requests_of_faculty/{id}")
    public String getRequestsForFacultyById(@PageableDefault(sort = {ADMISSION_REQUEST_STATUS}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
                                            @PathVariable(name = "id") Long id, Model model) {
        Page<AdmissionRequest> page = admissionRequestService.getAdmissionRequestsForFacultyWithId(id, pageable);

        model.addAttribute(PAGE, page);
        model.addAttribute(URL, "/admin/requests_of_faculty/" + id);

        return "/admin/requests_of_faculty";
    }

    @GetMapping("/admin/requests_of_faculty/request/{id}")
    public String getRequestById(@PathVariable(name = ID) Long id, Model model) {
        model.addAttribute(REQUEST, admissionRequestService.getById(id));
        return "/admin/request";
    }

    @PostMapping("/admin/request_update")
    public String requestApprove(AdmissionRequestDTO admissionRequestDTO) {
        admissionRequestService.updateStatusOfRequest(admissionRequestDTO);
        return "redirect:/admin/requests_of_faculty/" + admissionRequestDTO.getFacultyId();
    }
}
