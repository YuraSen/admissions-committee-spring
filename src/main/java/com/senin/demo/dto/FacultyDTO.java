package com.senin.demo.dto;

import com.senin.demo.entity.AdmissionRequestEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.NamedEntityGraph;
import javax.validation.constraints.AssertTrue;
import java.util.List;


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
        if (this.budgetCapacity != null && this.totalCapacity != null) {
            return this.budgetCapacity <= this.totalCapacity;
        }
        return false;
    }

    private List<AdmissionRequestEntity> admissionRequestEntities;

}
