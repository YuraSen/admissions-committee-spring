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
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String lastName;

    @Column(name = "last_name")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "school")
    private String school;

    @Column(name = "certificate_file")
    private String certificateFile;
}
