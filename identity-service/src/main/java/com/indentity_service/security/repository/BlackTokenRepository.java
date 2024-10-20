package com.indentity_service.security.repository;

import com.indentity_service.security.model.BlackToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlackTokenRepository extends JpaRepository<BlackToken, String> {
    Optional<BlackToken> findByToken(String token);
}
