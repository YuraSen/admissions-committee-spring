package com.senin.demo.repository;

import com.senin.demo.dto.Role;
import com.senin.demo.dto.UserStatus;
import com.senin.demo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity  c set c.role = :role," +
            "c.userStatus=:userStatus WHERE c.id = :id")
    int setUserUpdate(@Param("id") Long id,
                      @Param("role") Role role,
                      @Param("userStatus") UserStatus userStatus);


    Page<UserEntity> findAll(Pageable pageable);

    Optional<UserEntity> findByUsername(String username);
}
