package com.senin.demo.service.impl;

import com.senin.demo.dto.*;
import com.senin.demo.entity.UserEntity;
import com.senin.demo.entity.UserProfileEntity;
import com.senin.demo.exception.IncorrectIdRuntimeException;
import com.senin.demo.exception.UserAlreadyExistsException;
import com.senin.demo.repository.UserRepository;
import com.senin.demo.service.UserService;
import com.senin.demo.util.UtilityService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;
    private final PasswordEncoder bCryptPasswordEncoder;


    public UserDTO mapUserEntityToDTO(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserEntity mapUserDtoToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, UserEntity.class);
    }

    @Override
    public UserDTO findById(Long id) {
        UtilityService.isIdPositive(id);
        return mapUserEntityToDTO(userRepository.findById(id)
                .orElseThrow(() -> new IncorrectIdRuntimeException(UtilityService.ID_CORRECT)));
    }

    @Override
    public Page<UserDTO> getAllCandidates(Pageable pageable) {
        Page<UserEntity> userRepositoryAll = userRepository.findAll(pageable);
        List<UserDTO> collect = userRepositoryAll.stream()
                .map(this::mapUserEntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, countAllUser());
    }

    private Long countAllUser() {
        return userRepository.count();
    }

    @Override
    public UserDTO findByUsername(String userName) {
        return mapUserEntityToDTO(userRepository.findByUsername(userName).orElse(new UserEntity()));
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO user) {
        return mapUserEntityToDTO(entityManager.merge(mapUserDtoToEntity(user)));
    }

    @Override
    public UserEntity createUser(UserDTO userDTO, UserProfileDTO userProfileDTO) {
        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .role(Role.USER)
                .userStatus(UserStatus.ACTIVE)
                .build();

        UserProfileEntity userProfileEntity = UserProfileEntity.builder()
                .firstName(userProfileDTO.getFirstName())
                .lastName(userProfileDTO.getLastName())
                .email(userProfileDTO.getEmail())
                .city(userProfileDTO.getCity())
                .region(userProfileDTO.getRegion())
                .school(userProfileDTO.getSchool())
                .firstName(userProfileDTO.getCertificateFile())
                .userEntity(userEntity)
                .build();
        userEntity.setUserProfileEntity(userProfileEntity);
        try {
            userEntity = userRepository.save(userEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new UserAlreadyExistsException("User already Exists!");
        }
        return userEntity;
    }


    @Override
    public void setUserRequestsStatus(UserDTO userDTO) {

    }

    @Override
    public void deleteById(Long id) {
        UtilityService.isIdPositive(id);
        userRepository.deleteById(id);
    }

    @Override
    public Integer updateUserProfile(UserProfileDTO userProfileDTO) {
        return null;
    }

    @Override
    public String saveFile(MultipartFile file, String uploadPath) {
        return null;
    }
}
