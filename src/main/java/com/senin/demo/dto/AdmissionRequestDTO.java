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
    private static final String RANGE_MARK = "assessment should be from [1 : 12]";
    private static final String NOT_EMPTY = "can not be empty";

    private Long id;

    private Long applicantId;

    private Long facultyId;

    private Applicant applicant;

    private Faculty faculty;

    @NotNull(message = NOT_EMPTY)
    @Range(min = 1, max = 12, message = RANGE_MARK)
    private Integer requiredSubject1Grade;

    @NotNull(message = NOT_EMPTY)
    @Range(min = 1, max = 12, message = RANGE_MARK)
    private Integer requiredSubject2Grade;

    @NotNull(message = NOT_EMPTY)
    @Range(min = 1, max = 12, message = RANGE_MARK)
    private Integer requiredSubject3Grade;

    private AdmissionRequestStatus admissionRequestStatus;
}
