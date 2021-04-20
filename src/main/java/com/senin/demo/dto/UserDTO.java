package com.senin.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.NamedEntityGraph;

@Data
@AllArgsConstructor
@NamedEntityGraph
@Builder
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private Role role;
}
