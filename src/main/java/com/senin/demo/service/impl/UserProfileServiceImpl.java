package com.senin.demo.service.impl;

import com.senin.demo.dto.UserDTO;
import com.senin.demo.dto.UserProfileDTO;
import com.senin.demo.exception.UserAlreadyExistsException;
import com.senin.demo.repository.UserProfileRepository;
import com.senin.demo.service.UserProfileService;
import com.senin.demo.service.mapper.UserProfileMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
    public UserProfileDTO createUser(UserProfileDTO userProfileDTO) {
        return profileMapper
                .mapUserProfileEntityToDTO(userProfileRepository
                        .save(profileMapper
                                .mapUserProfileDTOToEntity(userProfileDTO)));
    }
}
