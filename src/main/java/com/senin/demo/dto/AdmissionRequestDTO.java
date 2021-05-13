package com.senin.demo.dto;

import com.senin.demo.entity.Applicant;
import com.senin.demo.entity.Faculty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdmissionRequestDTO {
    private Long id;

    private Long applicantId;

    private Long facultyId;

    private Applicant applicant;

    private Faculty faculty;

    @NotNull(message = "can not be empty")
    @Range(min = 1, max = 12, message = "assessment should be from [1 : 12]")
    private Integer requiredSubject1Grade;

    @NotNull(message = "can not be empty")
    @Range(min = 1, max = 12, message = "assessment should be from [1 : 12]")
    private Integer requiredSubject2Grade;

    @NotNull(message = "can not be empty")
    @Range(min = 1, max = 12, message = "assessment should be from [1 : 12]")
    private Integer requiredSubject3Grade;

    private AdmissionRequestStatus admissionRequestStatus;
}
