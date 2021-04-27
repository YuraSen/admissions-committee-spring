package com.senin.demo.dto;

import com.senin.demo.entity.AdmissionRequestEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.NamedEntityGraph;
import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;


@Data
@AllArgsConstructor
@NamedEntityGraph
@Builder
public class FacultyDTO {
    private Long id;

    private String name;

    private String description;

    private Integer totalCapacity;

    private Integer budgetCapacity;

    private String firstRequiredSubject;

    private String secondRequiredSubject;

    private String thirdRequiredSubject;

    private boolean admissionOpen;

    @AssertTrue(message = "budget capacity must be less or equals to total capacity")
    private boolean isValid() {
        return nonNull(budgetCapacity) && nonNull(totalCapacity) && budgetCapacity <= totalCapacity;
    }

    private List<AdmissionRequestEntity> admissionRequestEntities;

}
