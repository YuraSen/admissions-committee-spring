package com.senin.demo.service;

import com.senin.demo.dto.UserDTO;
import com.senin.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDTO save(UserDTO user);

    UserDTO findById(Long id);

    UserDTO findByUserName(String userName);

    List<UserDTO> findAll();

    UserDTO update(UserDTO user);

    void deleteById(Long id);
}
