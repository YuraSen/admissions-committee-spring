package com.senin.demo.controller;

import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.entity.Faculty;
import com.senin.demo.service.FacultyService;
import com.senin.demo.util.ValidationErrorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import static com.senin.demo.controller.ControllerAttributeConstant.*;

@Slf4j
@Controller
@AllArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;

    @GetMapping("/faculties")
    public String getAllFaculties(@PageableDefault(sort = {"nameEn"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
                                  @AuthenticationPrincipal User user, Model model) {

        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "redirect:/admin/workspace";
        } else {
            Page<Faculty> page = facultyService.getAllFaculties(pageable);
            model.addAttribute(PAGE, page);
            model.addAttribute(URL, "/faculties");
            return "faculties";
        }
    }


    @GetMapping("/admin/faculty/add")
    public String createNewFacultyForm(Model model) {
        return "/admin/create-faculty";
    }

    @PostMapping("/admin/faculty/add")
    public String createNewFaculty(@Valid FacultyDTO facultyDTO,
                                   Errors errors,
                                   Model model) {
        if (errors.hasErrors()) {
            model.mergeAttributes(ValidationErrorUtils.getErrorsMap(errors));
            model.addAttribute(FACULTY, facultyDTO);
            return "/admin/create-faculty";
        }
        facultyService.createFaculty(facultyDTO);

        return "redirect:/admin/workspace";
    }


    @PostMapping("/admin/block_reg/{id}")
    public String blockRegistrationToFaculty(FacultyDTO facultyDTO) {
        facultyService.blockUnblockRegistration(facultyDTO);
        return "redirect:/admin/workspace";
    }


    @GetMapping("/admin/faculty/edit/{id}")
    public String editFacultyWithId(@PathVariable Long id, Model model) {
        model.addAttribute(FACULTY, facultyService.getById(id));
        return "/admin/edit-faculty";

    }


    @PostMapping("/admin/faculty/edit/{id}")
    public String updateFacultyWithId(@Valid FacultyDTO facultyDTO,
                                      Errors errors,
                                      Model model) {
        if (errors.hasErrors()) {
            model.mergeAttributes(ValidationErrorUtils.getErrorsMap(errors));
            model.addAttribute(FACULTY, facultyDTO);
            return "/admin/edit-faculty";
        }


        facultyService.createFaculty(facultyDTO);
        return "redirect:/admin/workspace";

    }


    @PostMapping("/admin/faculties/delete/{id}")
    public String deleteFacultyById(@PathVariable(name = "id") Long id) {
        facultyService.deleteById(id);
        return "redirect:/admin/workspace";
    }


    @GetMapping("/admin/workspace")
    public String getAdminWorkspace(@PageableDefault(sort = {"nameEn"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
                                    Model model) {

        Page<Faculty> page = facultyService.getAllFaculties(pageable);
        model.addAttribute(PAGE, page);
        model.addAttribute(URL, "/admin/workspace");
        return "/admin/admin_workspace";

    }
}
