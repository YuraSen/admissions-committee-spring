package com.senin.demo.service;

import com.senin.demo.entity.Applicant;
import com.senin.demo.repository.ApplicantRepository;
import com.senin.demo.security.SecurityUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service("userDetailsServiceImpl")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String USER_NOT_EXISTS = "User doesn't exists";

    private final ApplicantRepository applicantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Applicant applicant = applicantRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_EXISTS));
        return SecurityUser.getUserFromApplicant(applicant);
    }
}
