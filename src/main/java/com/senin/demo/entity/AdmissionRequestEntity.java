package com.senin.demo.entity;

import com.senin.demo.dto.AdmissionRequestStatus;
import com.senin.demo.dto.UserStatus;
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
@Table(name = "admission_request")
public class AdmissionRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "status", nullable = false)
    private AdmissionRequestStatus admissionRequestStatus;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(targetEntity = FacultyEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    private FacultyEntity facultyEntity;

    @Column(name = "first_RequiredSubject_Mark", nullable = false)
    private Integer firstRequiredSubjectMark;
    @Column(name = "second_Required_Subject_Mark", nullable = false)
    private Integer secondRequiredSubjectMark;
    @Column(name = "third_Required_Subject_Mark", nullable = false)
    private Integer thirdRequiredSubjectMark;

    @CreationTimestamp
    @Column(name = "creation_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDateTime;


}
