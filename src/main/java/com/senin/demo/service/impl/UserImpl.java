package com.senin.demo.service.impl;

import com.senin.demo.dto.UserDTO;
import com.senin.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserImpl implements UserService {
    @Override
    public UserDTO save(UserDTO user) {
        return null;
    }

    @Override
    public UserDTO findById(Long id) {
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public UserDTO update(UserDTO user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
