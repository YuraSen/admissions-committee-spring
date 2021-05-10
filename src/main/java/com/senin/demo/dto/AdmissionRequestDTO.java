package com.senin.demo.dto;

import com.senin.demo.entity.Faculty;
import com.senin.demo.entity.Candidate;
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

    private Long candidateId;

    private Long facultyId;

    private Candidate candidate;

    private Faculty faculty;

    @NotNull(message = "can not be empty")
    @Range(min = 1, max = 12, message = "grade should be from 1 to 12")
    private Integer requiredSubject1Grade;

    @NotNull(message = "can not be empty")
    @Range(min = 1, max = 12, message = "grade should be from 1 to 12")
    private Integer requiredSubject2Grade;

    @NotNull(message = "can not be empty")
    @Range(min = 1, max = 12, message = "grade should be from 1 to 12")
    private Integer requiredSubject3Grade;

    private AdmissionRequestStatus admissionRequestStatus;
}
