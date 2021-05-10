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
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name_en", nullable = false)
    private String nameEn;
    @Column(name = "name_uk", nullable = false)
    private String nameUk;

    @Column(name = "description_en", length = 2048, nullable = false)
    private String descriptionEn;
    @Column(name = "description_uk", length = 2048, nullable = false)
    private String descriptionUk;
    @Column(name = "budget_capacity", nullable = false)
    private int budgetCapacity;
    @Column(name = "total_capacity", nullable = false)
    private int totalCapacity;

    @Column(name = "req_subject1_en", nullable = false)
    private String requiredSubject1En;
    @Column(name = "req_subject1_uk", nullable = false)
    private String requiredSubject1Uk;
    @Column(name = "req_subject2_en", nullable = false)
    private String requiredSubject2En;
    @Column(name = "req_subject2_uk", nullable = false)
    private String requiredSubject2Uk;
    @Column(name = "req_subject3_en", nullable = false)
    private String requiredSubject3En;
    @Column(name = "req_subject3_uk", nullable = false)
    private String requiredSubject3Uk;
    @Column(name = "admission_open", nullable = false)
    private boolean admissionOpen;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdmissionRequest> admissionRequestList;

    public Long numberOfRequestsNew() {
        return admissionRequestList.stream()
                .filter(ar -> ar.getAdmissionRequestStatus().ordinal() == AdmissionRequestStatus.NEW.ordinal())
                .count();
    }

    public Long numberOfRequestsApproved() {
        return admissionRequestList.stream()
                .filter(ar -> ar.getAdmissionRequestStatus().ordinal() == AdmissionRequestStatus.APPROVED.ordinal())
                .count();
    }

    public Long numberOfRequestsRejected() {
        return admissionRequestList.stream()
                .filter(ar -> ar.getAdmissionRequestStatus().ordinal() == AdmissionRequestStatus.REJECTED.ordinal())
                .count();
    }
}
