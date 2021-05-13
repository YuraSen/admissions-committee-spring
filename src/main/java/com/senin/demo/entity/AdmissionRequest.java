package com.senin.demo.entity;

import com.senin.demo.dto.AdmissionRequestStatus;
import com.senin.demo.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "admission_request",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"applicant_id", "faculty_id"})})
public class AdmissionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "status", nullable = false)
    private AdmissionRequestStatus admissionRequestStatus;

    @ManyToOne(targetEntity = Applicant.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @ManyToOne(targetEntity = Faculty.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(name = "req_subject1_grade", nullable = false)
    private int requiredSubject1Grade;
    @Column(name = "req_subject2_grade", nullable = false)
    private int requiredSubject2Grade;
    @Column(name = "req_subject3_grade", nullable = false)
    private int requiredSubject3Grade;

    @CreationTimestamp
    @Column(name = "creation_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDateTime;

    public int getSumOfGrades() {
        return getRequiredSubject1Grade() + getRequiredSubject2Grade() + getRequiredSubject3Grade();
    }

    public String getStringDateTime() {
        return DateTimeUtil.toString(getCreationDateTime());

    }
}
