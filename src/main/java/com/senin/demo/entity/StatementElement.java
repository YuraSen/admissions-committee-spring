package com.senin.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatementElement {
    private String facultyName;

    private String firstName;

    private String lastName;

    private String email;

    private int grade;

    private String status;

}
