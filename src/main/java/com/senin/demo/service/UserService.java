package com.senin.demo.service;

import com.senin.demo.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDTO save(UserDTO user);

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    UserDTO update(UserDTO user);

    void deleteById(Long id);
}
