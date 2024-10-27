package com.devhuunhan.security.repository;

import com.devhuunhan.security.model.BlackToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlackTokenRepository extends JpaRepository<BlackToken, String> {
    Optional<BlackToken> findByToken(String token);
}
