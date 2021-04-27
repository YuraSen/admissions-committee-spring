package com.senin.demo.service.impl;

import com.senin.demo.dto.*;
import com.senin.demo.entity.UserEntity;
import com.senin.demo.entity.UserProfileEntity;
import com.senin.demo.exception.IncorrectIdRuntimeException;
import com.senin.demo.exception.UserAlreadyExistsException;
import com.senin.demo.repository.UserRepository;
import com.senin.demo.service.UserService;
import com.senin.demo.service.mapper.UserMapper;
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
    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDTO findById(Long id) {
        UtilityService.isIdPositive(id);
        return userMapper.mapUserEntityToDTO(userRepository.findById(id)
                .orElseThrow(() -> new IncorrectIdRuntimeException(UtilityService.ID_CORRECT)));
    }

    @Override
    public Page<UserDTO> getAllCandidates(Pageable pageable) {
        Page<UserEntity> userRepositoryAll = userRepository.findAll(pageable);
        List<UserDTO> collect = userRepositoryAll.stream()
                .map(userMapper::mapUserEntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, countAllUser());
    }

    private Long countAllUser() {
        return userRepository.count();
    }

    @Override
    public UserDTO findByUsername(String userName) {
        return userMapper.mapUserEntityToDTO(userRepository.findByUsername(userName).orElse(new UserEntity()));
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO user) {
        return userMapper.mapUserEntityToDTO(entityManager.merge(userMapper.mapUserDTOToEntity(user)));
    }

    @Override
    public UserEntity createUser(UserDTO userDTO, UserProfileDTO userProfileDTO) {
        return null;
    }


    @Override
    public void deleteById(Long id) {
        UtilityService.isIdPositive(id);
        userRepository.deleteById(id);
    }

    @Override
    public void setUserRequestsStatus(UserDTO userDTO) {

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
