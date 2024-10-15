package com.profile_service.repository;

import com.profile_service.model.UserProfile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfile, String> {
    Optional<UserProfile> findByEmail(String email);
    Optional<UserProfile> findByPhone(String phone);
    Optional<UserProfile> findByUsername(String username);
}
