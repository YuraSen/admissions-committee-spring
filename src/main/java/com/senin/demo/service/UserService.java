package com.senin.demo.service;

import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.dto.UserDTO;
import com.senin.demo.dto.UserProfileDTO;
import com.senin.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserDTO findById(Long id);

    Page<UserDTO> getAllCandidates(Pageable pageable);

    UserDTO findByUsername(String userName);

    UserDTO update(UserDTO user);

    UserEntity createUser(UserDTO userDTO);

    void setUserRequestsStatus(UserDTO userDTO);

    void deleteById(Long id);

    String saveFile(MultipartFile file, String uploadPath);

}
