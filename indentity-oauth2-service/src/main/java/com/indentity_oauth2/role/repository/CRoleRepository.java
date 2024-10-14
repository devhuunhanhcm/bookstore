package com.indentity_oauth2.role.repository;

import com.indentity_oauth2.role.model.CRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CRoleRepository extends JpaRepository<CRole, String> {
    Optional<CRole> findByCode(String roleCode);
}
