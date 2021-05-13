package com.senin.demo.service;

import com.senin.demo.entity.Applicant;
import com.senin.demo.repository.ApplicantRepository;
import com.senin.demo.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicantRepository applicantRepository;

    public UserDetailsServiceImpl(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Applicant applicant = applicantRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.getUserFromApplicant(applicant);
    }
}
