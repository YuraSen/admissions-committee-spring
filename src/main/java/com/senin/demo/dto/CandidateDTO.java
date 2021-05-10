package com.senin.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateDTO {
    private Long id;

    private String username;

    private String password;

    private Role role;

    private CandidateStatus candidateStatus;

}
