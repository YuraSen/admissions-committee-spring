package com.senin.demo.repository;

import com.senin.demo.entity.FacultyEntity;
import com.senin.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<FacultyEntity, Long> {

}
