package com.senin.demo.service.impl;

import com.senin.demo.dto.UserDTO;
import com.senin.demo.entity.UserEntity;
import com.senin.demo.exception.IncorrectIdRuntimeException;
import com.senin.demo.exception.IncorrectUserNameException;
import com.senin.demo.repository.UserRepository;
import com.senin.demo.service.UserService;
import com.senin.demo.util.UtilityService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserImpl implements UserService {
    @PersistenceContext
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    public UserDTO mapUserEntityToDTO(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserEntity mapUserDtoToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, UserEntity.class);
    }

    @Override
    public UserDTO save(UserDTO user) {
        return mapUserEntityToDTO(userRepository.save(mapUserDtoToEntity(user)));
    }

    @Override
    public UserDTO findById(Long id) {
        UtilityService.isIdPositive(id);
        return mapUserEntityToDTO(userRepository.findById(id)
                .orElseThrow(() -> new IncorrectIdRuntimeException(UtilityService.ID_CORRECT)));
    }

    @Override
    public UserDTO findByUserName(String username){
        UtilityService.isNamePresent(username);
        return mapUserEntityToDTO(userRepository.findByUsername(username)
        .orElseThrow(() -> new IncorrectUserNameException(UtilityService.USERNAME_CORRECT)));
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(this::mapUserEntityToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO user) {
        return mapUserEntityToDTO(entityManager.merge(mapUserDtoToEntity(user)));
    }

    @Override
    public void deleteById(Long id) {
        UtilityService.isIdPositive(id);
        userRepository.deleteById(id);
    }
}
