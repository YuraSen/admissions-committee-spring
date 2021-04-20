package com.senin.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.NamedEntityGraph;

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
}
