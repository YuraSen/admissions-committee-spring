package com.senin.demo.controller;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.entity.AdmissionRequest;
import com.senin.demo.exception.RequestAlreadyExistsException;
import com.senin.demo.service.AdmissionRequestService;
import com.senin.demo.util.ValidationErrorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

@Slf4j
@Controller
@AllArgsConstructor
public class AdmissionRequestController {
    private final AdmissionRequestService admissionRequestService;

    @GetMapping("/candidate/submit_request_form")
    public String getRequestForm(FacultyDTO facultyDTO,
                                 @AuthenticationPrincipal User currentUser, Model model) {

        if (facultyDTO.isAdmissionOpen()) {
            AdmissionRequestDTO admissionRequestDTO = admissionRequestService.getAdmissionRequestDTO(facultyDTO.getId(), currentUser.getUsername());
            model.addAttribute("faculty", admissionRequestDTO.getFaculty());
            model.addAttribute("candidate", admissionRequestDTO.getCandidate());
            return "/candidate/request_form";
        }
        return "/candidate/candidate_requests";
    }

    @PostMapping("/candidate/submit_request")
    public String createRequestFromCandidate(
            @Valid AdmissionRequestDTO admissionRequestDTO,
            Errors errors, @AuthenticationPrincipal User currentUser,
            Model model) {
        AdmissionRequestDTO admissionRequest = admissionRequestService
                .getAdmissionRequestDTO(admissionRequestDTO.getFacultyId(), currentUser.getUsername());
        model.addAttribute("facultyId", admissionRequest.getFaculty().getId());
        model.addAttribute("faculty", admissionRequest.getFaculty());
        model.addAttribute("candidate", admissionRequest.getCandidate());
        if (errors.hasErrors()) {
            model.mergeAttributes(ValidationErrorUtils.getErrorsMap(errors));
            return "/candidate/request_form";
        }
        try {
            admissionRequestService.saveAdmissionRequest(admissionRequestDTO);
        } catch (RequestAlreadyExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/candidate/request_form";
        }
        return "redirect:/candidate/candidate_requests";
    }

    @GetMapping("/candidate/candidate_requests")
    public String getAllUserRequests(@PageableDefault(sort = {"id"},
            direction = Sort.Direction.ASC, size = 5) Pageable pageable,
                                     @AuthenticationPrincipal User currentUser
            , Model model) {

        Page<AdmissionRequest> page = admissionRequestService.getAdmissionRequestsForUserWithUsername(currentUser.getUsername(), pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/candidate/candidate_requests");
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("requests_list", page);
        return "/candidate/candidate_requests";
    }

    @PostMapping("/candidate/delete_request/{id}")
    public String deleteRequest(@PathVariable(name = "id") Long id) {
        admissionRequestService.deleteRequest(id);
        return "redirect:/candidate/candidate_requests";
    }

    @GetMapping("/admin/requests_of_faculty/{id}")
    public String getRequestsForFacultyById(@PageableDefault(sort = {"admissionRequestStatus"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
                                            @PathVariable(name = "id") Long id, Model model) {
        Page<AdmissionRequest> page = admissionRequestService.getAdmissionRequestsForFacultyWithId(id, pageable);

        model.addAttribute("page", page);
        model.addAttribute("url", "/admin/requests_of_faculty/" + id);

        return "/admin/requests_of_faculty";
    }

    @GetMapping("/admin/requests_of_faculty/request/{id}")
    public String getRequestById(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("request", admissionRequestService.getById(id));
        return "/admin/request";
    }

    @PostMapping("/admin/request_update")
    public String requestApprove(AdmissionRequestDTO admissionRequestDTO) {
        admissionRequestService.updateStatusOfRequest(admissionRequestDTO);
        return "redirect:/admin/requests_of_faculty/" + admissionRequestDTO.getFacultyId();
    }
}
