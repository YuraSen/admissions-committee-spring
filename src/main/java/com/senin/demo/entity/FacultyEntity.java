package com.senin.demo.entity;

import com.senin.demo.dto.AdmissionRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "admission_open", nullable = false)
    private boolean admissionOpen;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdmissionRequestEntity> admissionRequestEntityList;


    public Long numberOfNewRequests() {
        return admissionRequestEntityList.stream()
                .filter(ar -> ar.getAdmissionRequestStatus().ordinal() == AdmissionRequestStatus.NEW.ordinal())
                .count();
    }

    public Long numberOfApprovedRequests() {
        return admissionRequestEntityList.stream()
                .filter(ar -> ar.getAdmissionRequestStatus().ordinal() == AdmissionRequestStatus.APPROVED.ordinal())
                .count();
    }

    public Long numberOfRejectedRequests() {
        return admissionRequestEntityList.stream()
                .filter(ar -> ar.getAdmissionRequestStatus().ordinal() == AdmissionRequestStatus.REJECTED.ordinal())
                .count();
    }
}
