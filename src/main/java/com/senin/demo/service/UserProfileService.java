package com.senin.demo.service;

import com.senin.demo.dto.UserDTO;
import com.senin.demo.dto.UserProfileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserProfileService {
    UserProfileDTO updateUserProfile(UserProfileDTO userProfileDTO);

    String saveFile(MultipartFile file, String uploadPath) throws IOException;

    UserProfileDTO findByUsername(String username);

    UserProfileDTO createUser(UserProfileDTO userProfileDTO);

}
