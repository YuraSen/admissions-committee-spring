package com.senin.demo.service;

import com.senin.demo.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    UserDTO findById(Long id);

    Page<UserDTO> getAllUser(Pageable pageable);

    UserDTO findByUsername(String userName);

    UserDTO update(UserDTO user);

    UserDTO createUser(UserDTO userDTO);

    void setUserRequestsStatus(UserDTO userDTO);

    void deleteById(Long id);

    String saveFile(MultipartFile file, String uploadPath) throws IOException;

}
