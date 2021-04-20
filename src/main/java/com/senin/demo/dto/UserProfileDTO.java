package com.senin.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.NamedEntityGraph;

@Data
@AllArgsConstructor
@NamedEntityGraph
@Builder
public class UserProfileDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private String region;

    private String school;

    private String certificateFile;
}
