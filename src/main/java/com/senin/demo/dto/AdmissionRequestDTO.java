package com.senin.demo.dto;

import com.senin.demo.entity.FacultyEntity;
import com.senin.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdmissionRequestDTO {
    private Long id;

    private Long candidateId;

    private Long facultyId;

    private UserEntity userEntity;

    private FacultyEntity facultyEntity;

    private Integer firstRequiredSubjectMark;

    private Integer secondRequiredSubjectMark;

    private Integer thirdRequiredSubjectMark;

    private AdmissionRequestStatus admissionRequestStatus;
}
