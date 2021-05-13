package com.senin.demo.security;

import com.senin.demo.dto.ApplicantStatus;
import com.senin.demo.entity.Applicant;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;
    private boolean isAdmin;


    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }


    public static UserDetails getUserFromApplicant(Applicant applicant) {
        return new org.springframework.security.core.userdetails.User(
                applicant.getUsername(),
                applicant.getPassword(),
                applicant.getApplicantStatus().equals(ApplicantStatus.ACTIVE),
                applicant.getApplicantStatus().equals(ApplicantStatus.ACTIVE),
                applicant.getApplicantStatus().equals(ApplicantStatus.ACTIVE),
                applicant.getApplicantStatus().equals(ApplicantStatus.ACTIVE),
                applicant.getAuthorities());

    }
}

