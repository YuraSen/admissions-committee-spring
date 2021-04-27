package com.senin.demo.service;

import com.senin.demo.dto.UserDTO;
import com.senin.demo.dto.UserProfileDTO;
import com.senin.demo.entity.UserEntity;

public interface UserProfileService {
    Boolean updateUserProfile(UserProfileDTO userProfileDTO);

    UserEntity createUser(UserProfileDTO userProfileDTO);

}
