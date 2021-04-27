package com.senin.demo.controller;

import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.service.impl.FacultyServiceImpl;
import com.senin.demo.util.UtilityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyServiceImpl facultyService;

    @GetMapping("/faculties")
    public String getAllFaculties(@PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
                                  @AuthenticationPrincipal User user, Model model) {

        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "redirect:/admin/workspace";
        } else {
            Page<FacultyDTO> page = facultyService.getAllFaculties(pageable);
            model.addAttribute("page", page);
            model.addAttribute("url", "/faculties");
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
            model.mergeAttributes(UtilityService.getErrorsMap(errors));
            model.addAttribute("faculty", facultyDTO);
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
        model.addAttribute("faculty", facultyService.findById(id));
        return "/admin/edit-faculty";

    }


    @PostMapping("/admin/faculty/edit/{id}")
    public String updateFacultyWithId(@Valid FacultyDTO facultyDTO,
                                      Errors errors,
                                      Model model) {
        if (errors.hasErrors()) {
            model.mergeAttributes(UtilityService.getErrorsMap(errors));
            model.addAttribute("faculty", facultyDTO);
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

        Page<FacultyDTO> page = facultyService.getAllFaculties(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/admin/workspace");
        return "/admin/admin_workspace";

    }
}
