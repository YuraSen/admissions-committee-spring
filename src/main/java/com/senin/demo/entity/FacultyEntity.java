package com.senin.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

@Table(name = "faculty")
public class FacultyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "total_capacity")
    private Integer totalCapacity;

    @Column(name = "budget_capacity")
    private Integer budgetCapacity;

    @Column(name = "first_required_subject")
    private String firstRequiredSubject;

    @Column(name = "second_required_subject")
    private String secondRequiredSubject;

    @Column(name = "third_required_subject")
    private String thirdRequiredSubject;
}
