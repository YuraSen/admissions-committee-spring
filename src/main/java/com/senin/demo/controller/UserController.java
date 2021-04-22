package com.senin.demo.controller;


import com.senin.demo.dto.UserDTO;
import com.senin.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public List<UserDTO> showAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO showById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("/")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO edit(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}