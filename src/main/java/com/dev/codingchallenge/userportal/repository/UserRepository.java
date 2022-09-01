package com.dev.codingchallenge.userportal.repository;

import com.dev.codingchallenge.userportal.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
}
