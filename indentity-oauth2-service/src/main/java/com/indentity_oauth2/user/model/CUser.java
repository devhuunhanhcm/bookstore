package com.indentity_oauth2.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indentity_oauth2.common.model.BaseEntity;
import com.indentity_oauth2.role.model.CRole;
import com.indentity_oauth2.security.model.RefreshToken;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name="c_user")
public class CUser extends BaseEntity {

    @Column(name = "username",unique = true,nullable = false,length=100)
    private String username;

    @JsonIgnore
    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false,unique = true,length = 255)
    private String email;

    @Column(name = "is_active",columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean isActive = true;

    @Builder.Default
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="c_user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<CRole> roles = new LinkedHashSet<CRole>();

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RefreshToken> refreshTokens = new LinkedHashSet<>();
}
