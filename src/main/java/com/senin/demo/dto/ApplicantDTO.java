package com.senin.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantDTO {
    private Long id;

    @NotBlank(message = "{username.not_empty}")
    @Length( max = 15, message ="{username.too_long} ")
    private String username;
    @NotBlank(message = "{password.not_empty}")
    @Length( max = 15, message = "{password.too_long}")
    private String password;

    private Role role;

    private ApplicantStatus applicantStatus;

}
