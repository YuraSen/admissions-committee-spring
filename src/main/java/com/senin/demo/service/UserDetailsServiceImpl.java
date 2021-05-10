package com.senin.demo.service;

import com.senin.demo.entity.Candidate;
import com.senin.demo.repository.CandidateRepository;
import com.senin.demo.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CandidateRepository candidateRepository;

    public UserDetailsServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Candidate candidate = candidateRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does't exists"));
        return SecurityUser.getUserFromCandidate(candidate);
    }
}
