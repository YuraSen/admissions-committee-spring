package com.senin.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.NamedEntityGraph;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
