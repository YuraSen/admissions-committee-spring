package com.senin.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.NamedEntityGraph;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantProfileDTO {
    private Long id;

    @NotBlank(message = "{first_name.not_empty}")
    @Length(max = 25, message = "{first_name.too_long}")
    private String firstName;

    @NotBlank(message = "{last_name.not_empty}")
    @Length(max = 25, message = "{address_name.not_empty}")
    private String lastName;

    @Email(message = "{email.not_valid}")
    @NotBlank(message = "{email.not_empty}")
    private String email;

    @NotBlank(message = "{address_name.not_empty}")
    @Length(max = 40, message = "{address_name.too_long}")
    private String address;

    @NotBlank(message = "{city.not_empty}")
    @Length(max = 25, message = "{city_name.too_long}")
    private String city;

    @NotBlank(message = "{region.not_empty}")
    @Length(max = 25, message = "{region_name.too_long}")
    private String region;

    @NotBlank(message = "{school.not_empty}")
    @Length(max = 40, message = "{school_name.too_long}")
    private String school;

    @NotBlank(message = "{phoneNumber.not_empty}")
    @Pattern(regexp = "[0-9]+", message = "{phoneNumber.wrong_format}")
    private String phoneNumber;

    private Long applicantId;

    private String fileName;
}
