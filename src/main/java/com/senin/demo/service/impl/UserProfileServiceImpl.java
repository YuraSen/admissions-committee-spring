package com.senin.demo.service.impl;

import com.senin.demo.dto.UserDTO;
import com.senin.demo.dto.UserProfileDTO;
import com.senin.demo.entity.UserEntity;
import com.senin.demo.entity.UserProfileEntity;
import com.senin.demo.repository.UserProfileRepository;
import com.senin.demo.service.UserProfileService;
import com.senin.demo.service.mapper.UserProfileMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserProfileServiceImpl implements UserProfileService {
    @PersistenceContext
    private final UserProfileRepository userProfileRepository;
    private final EntityManager entityManager;
    private final UserProfileMapper profileMapper;

    @Override
    @Transactional
    public UserProfileDTO updateUserProfile(UserProfileDTO userProfileDTO) {
        return profileMapper
                .mapUserProfileEntityToDTO(entityManager
                        .merge(profileMapper.mapUserProfileDTOToEntity(userProfileDTO)));
    }

    @Override
    public UserProfileDTO findByUsername(String username) {
        return profileMapper.mapUserProfileEntityToDTO(userProfileRepository.findByUserEntityUsername(username).orElse(new UserProfileEntity()));
    }

    @Override
    public UserProfileDTO createUser(UserProfileDTO userProfileDTO) {
        return profileMapper
                .mapUserProfileEntityToDTO(userProfileRepository
                        .save(profileMapper
                                .mapUserProfileDTOToEntity(userProfileDTO)));
    }
    @Override
    public String saveFile(MultipartFile file, String uploadPath) throws IOException {
        String resultFilename = null;
        if (Objects.nonNull(file) && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
        }
        return resultFilename;
    }
}
