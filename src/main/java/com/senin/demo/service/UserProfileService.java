package com.senin.demo.service;

import com.senin.demo.dto.UserProfileDTO;

public interface UserProfileService {
    UserProfileDTO updateUserProfile(UserProfileDTO userProfileDTO);

    UserProfileDTO createUser(UserProfileDTO userProfileDTO);

}
