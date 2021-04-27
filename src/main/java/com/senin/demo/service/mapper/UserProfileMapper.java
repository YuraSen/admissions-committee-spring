package com.senin.demo.service.mapper;

import com.senin.demo.dto.UserProfileDTO;
import com.senin.demo.entity.UserProfileEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserProfileMapper {
    UserProfileDTO mapUserProfileEntityToDTO(UserProfileEntity userProfileEntity);

    UserProfileEntity mapUserProfileDTOToEntity(UserProfileDTO userProfileDTO);
}
