package com.dev.codingchallenge.userportal.repository;


import com.dev.codingchallenge.userportal.entity.ERole;
import com.dev.codingchallenge.userportal.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
