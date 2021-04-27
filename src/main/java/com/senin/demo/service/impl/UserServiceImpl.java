package com.senin.demo.service.impl;

import com.senin.demo.dto.AdmissionRequestStatus;
import com.senin.demo.dto.UserDTO;
import com.senin.demo.dto.UserStatus;
import com.senin.demo.entity.AdmissionRequestEntity;
import com.senin.demo.entity.UserEntity;
import com.senin.demo.exception.IncorrectIdRuntimeException;
import com.senin.demo.exception.UserAlreadyExistsException;
import com.senin.demo.repository.UserRepository;
import com.senin.demo.service.UserService;
import com.senin.demo.service.mapper.UserMapper;
import com.senin.demo.util.UtilityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final UserMapper userMapper;

    @Override
    public UserDTO findById(Long id) {
        UtilityService.isIdPositive(id);
        return userMapper.mapUserEntityToDTO(userRepository.findById(id)
                .orElseThrow(() -> new IncorrectIdRuntimeException(UtilityService.ID_CORRECT)));
    }

    @Override
    public Page<UserDTO> getAllUser(Pageable pageable) {
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
        return userMapper
                .mapUserEntityToDTO(entityManager
                        .merge(userMapper
                                .mapUserDTOToEntity(user)));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        try {
            return userMapper
                    .mapUserEntityToDTO(userRepository
                            .save(userMapper
                                    .mapUserDTOToEntity(userDTO)));
        } catch (DataIntegrityViolationException ex) {
            throw new UserAlreadyExistsException("User already exists!");
        }
    }

    @Override
    public void deleteById(Long id) {
        UtilityService.isIdPositive(id);
        userRepository.deleteById(id);
    }

    @Override
    public void setUserRequestsStatus(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userDTO.getId()).orElseThrow();
        if (userDTO.getUserStatus() == UserStatus.BLOCKED) {
            for (AdmissionRequestEntity ar : userEntity.getAdmissionRequestEntityList()) {
                ar.setAdmissionRequestStatus(AdmissionRequestStatus.REJECTED);
            }
        }
        if (userDTO.getUserStatus() == UserStatus.ACTIVE && userEntity.getUserStatus() == UserStatus.BLOCKED) {
            for (AdmissionRequestEntity ar : userEntity.getAdmissionRequestEntityList()) {
                ar.setAdmissionRequestStatus(AdmissionRequestStatus.NEW);
            }
        }
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
