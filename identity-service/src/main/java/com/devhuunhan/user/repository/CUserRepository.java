package com.devhuunhan.user.repository;

import com.devhuunhan.user.model.CUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CUserRepository extends JpaRepository<CUser, String> {
    Optional<CUser> findByUsername(String username);

    Optional<CUser> findByEmail(String email);
}