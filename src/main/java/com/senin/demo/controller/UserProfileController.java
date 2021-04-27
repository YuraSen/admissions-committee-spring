package com.senin.demo.controller;

import com.senin.demo.dto.UserProfileDTO;
import com.senin.demo.service.impl.UserProfileServiceImpl;
import com.senin.demo.service.impl.UserServiceImpl;
import com.senin.demo.util.UtilityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/user_profile")
public class UserProfileController {
    @Value("${upload.path}")
    private final String uploadPath;
    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");
    private final UserProfileServiceImpl profileService;


    @PostMapping("/api/user/registration")
    public String createUser(@RequestParam("file") MultipartFile file,
                             @Valid UserProfileDTO userProfileDTO,
                             Errors errorsProfile, Model model) throws IOException {

        if (errorsProfile.hasErrors()) {
            model.mergeAttributes(UtilityService.getErrorsMap(errorsProfile));
            model.addAttribute("userProfileDTO", userProfileDTO);

            return "/registration";
        }
        if (contentTypes.contains(file.getContentType())) {
            userProfileDTO.setFileName(profileService.saveFile(file, uploadPath));
        } else {
            model.addAttribute("errorMessage", "Wrong file format. Required: image/png, image/jpeg, image/gif ");
            return "/user/request_form";
        }
        profileService.createUser(userProfileDTO);
        return "redirect:/auth/login";
    }

    @PostMapping("/api/user/update")
    public String updateUser(@RequestParam("file") MultipartFile file,
                             @AuthenticationPrincipal User currentUser,
                             @Valid UserProfileDTO userProfileDTO,
                             Errors errorsProfile, Model model) throws IOException {

        if (errorsProfile.hasErrors()) {
            model.mergeAttributes(UtilityService.getErrorsMap(errorsProfile));
            model.addAttribute("username", currentUser.getUsername());
            model.addAttribute("userProfileDTO", userProfileDTO);
            return "user/user_profile_edit";
        }
        if (!file.isEmpty()) {
            if (contentTypes.contains(file.getContentType())) {
                userProfileDTO.setFileName(profileService.saveFile(file, uploadPath));
            } else {
                model.addAttribute("errorMessage", "Wrong file format. Required: image/png, image/jpeg, image/gif ");
                return "user/user_profile_edit";
            }
        }
        profileService.updateUserProfile(userProfileDTO);
        return "redirect:/api/user/profile";
    }
}


