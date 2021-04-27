package com.senin.demo.controller;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.exception.RequestAlreadyExistsException;
import com.senin.demo.service.impl.AdmissionRequestServiceImpl;
import com.senin.demo.util.UtilityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/")
public class AdmissionRequestController {
    private final AdmissionRequestServiceImpl admissionRequestService;

    @GetMapping("/user/submit_request_form")
    public String getRequestForm(FacultyDTO facultyDTO,
                                 @AuthenticationPrincipal User currentUser, Model model) {

        if (facultyDTO.isAdmissionOpen()) {
            model.addAttribute("faculty", facultyDTO);
            model.addAttribute("user", currentUser);
            return "/user/request_form";
        }
        return "/user/user_requests";
    }


    @PostMapping("/user/submit_request")
    public String createRequestFromUser(
            @Valid AdmissionRequestDTO admissionRequestDTO,
            Errors errors, @AuthenticationPrincipal User currentUser,
            Model model) {
        model.addAttribute("facultyId", admissionRequestDTO.getFacultyId());
        model.addAttribute("faculty", admissionRequestDTO.getFacultyEntity());
        model.addAttribute("user", currentUser);
        if (errors.hasErrors()) {
            model.mergeAttributes(UtilityService.getErrorsMap(errors));
            return "/user/request_form";
        }
        try {
            admissionRequestService.saveAdmissionRequest(admissionRequestDTO);
        } catch (RequestAlreadyExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/user/request_form";
        }
        return "redirect:/user/user_requests";
    }


    @GetMapping("/user/user_requests")
    public String getAllUserRequests(@PageableDefault(sort = {"id"},
            direction = Sort.Direction.ASC, size = 5) Pageable pageable,
                                     @AuthenticationPrincipal User currentUser
            , Model model) {

        Page<AdmissionRequestDTO> page = admissionRequestService.getAdmissionRequestsForUserByUsername(currentUser.getUsername(), pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/user/user_requests");
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("requests_list", page);
        return "/user/user_requests";
    }


    @PostMapping("/user/delete_request/{id}")
    public String deleteRequest(@PathVariable(name = "id") Long id) {
        admissionRequestService.deleteById(id);
        return "redirect:/user/user_requests";
    }


    @GetMapping("/admin/requests_of_faculty/{id}")
    public String getRequestsForFacultyById(@PageableDefault(sort = {"admissionRequestStatus"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
                                            @PathVariable(name = "id") Long id, Model model) {
        Page<AdmissionRequestDTO> page = admissionRequestService.getAdmissionRequestsForFacultyById(id, pageable);

        model.addAttribute("page", page);
        model.addAttribute("url", "/admin/requests_of_faculty/" + id);

        return "/admin/requests_of_faculty";
    }


    @GetMapping("/admin/requests_of_faculty/request/{id}")
    public String getRequestById(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("request", admissionRequestService.findById(id));
        return "/admin/request";
    }


    @PostMapping("/admin/request_update")
    public String requestApprove(AdmissionRequestDTO admissionRequestDTO) {
        admissionRequestService.update(admissionRequestDTO);
        return "redirect:/admin/requests_of_faculty/" + admissionRequestDTO.getFacultyId();
    }
}
