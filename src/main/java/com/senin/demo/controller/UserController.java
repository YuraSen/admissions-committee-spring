package com.senin.demo.controller;


import com.senin.demo.dto.UserDTO;
import com.senin.demo.service.impl.UserServiceImpl;
import com.senin.demo.util.UtilityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/user")
public class UserController {
    @Value("${upload.path}")
    private String uploadPath;
    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");
    private final UserServiceImpl userService;

    @GetMapping("/api/user/registration")
    public String registrationRedirect() {
        return "/registration";
    }


    @PostMapping("/api/user/registration")
    public String createUser(@RequestParam("file") MultipartFile file,
                                  @Valid UserDTO userDTO,
                                  Errors errors, Model model){

        if (errors.hasErrors()) {
            model.mergeAttributes(UtilityService.getErrorsMap(errors));
            model.addAttribute("userDTO", userDTO);
            return "/registration";
        }
        if (contentTypes.contains(file.getContentType())) {
        } else {
            model.addAttribute("errorMessage", "Wrong file format. Required: image/png, image/jpeg, image/gif ");
            return "/user/request_form";
        }
        userService.createUser(userDTO);
        return "redirect:/auth/login";
    }


    @DeleteMapping("/api/user/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/api/user/edit/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("user", id);
        model.addAttribute("uploadPath", uploadPath);
        return "/user/user_profile_edit";

    }

    @GetMapping("/api/user/update")
    public String updateUserForm(@AuthenticationPrincipal User currentUser, Model model) {
        model.addAttribute("user", userService.findByUsername(currentUser.getUsername()));
        return "user/user_profile_edit";
    }

    @GetMapping("/admin/user")
    public String getAllUsers(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
            Model model) {
        model.addAttribute("page", pageable);
        model.addAttribute("url", "/admin/user");

        return "/admin/users";
    }

    @GetMapping("/admin/user/edit/{id}")
    public String getUserById(@PathVariable Long id, Model model) {

        model.addAttribute("user", id);
        return "/admin/users-edit";

    }

    @PostMapping("/admin/user/edit/{id}")
    public String updateUser(UserDTO userDTO, @PathVariable String id) {
        userService.update(userDTO);
        return "redirect:/admin/user";

    }

    @PostMapping("/admin/user/delete/{id}")
    public String deleteRequest(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/user";
    }
}