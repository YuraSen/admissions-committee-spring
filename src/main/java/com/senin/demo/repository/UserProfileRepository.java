package com.senin.demo.repository;

import com.senin.demo.entity.UserEntity;
import com.senin.demo.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
    Optional<UserProfileEntity> findByUserEntityUsername(String username);

}
