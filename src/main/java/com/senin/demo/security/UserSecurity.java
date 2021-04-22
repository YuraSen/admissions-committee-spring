package com.senin.demo.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@AllArgsConstructor
public class UserSecurity implements UserDetails {
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;
    private  boolean isAdmin;

//    public static UserDetails getUserFromCandidate(UserEntity userEntity) {
//        return new org.springframework.security.core.userdetails.User(
//                userEntity.getUsername(),
//                userEntity.getPassword(),
//                userEntity.getStatus().equals(Status.ACTIVE),
//                userEntity.getStatus().equals(Status.ACTIVE),
//                userEntity.getStatus().equals(Status.ACTIVE),
//                userEntity.getStatus().equals(Status.ACTIVE),
//                userEntity.getAuthorities());
//    }

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
}
