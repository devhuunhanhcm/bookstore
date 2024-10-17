package com.indentity_service.role.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indentity_service.common.model.BaseEntity;
import com.indentity_service.user.model.CUser;
import jakarta.persistence.*;
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
@Table(name="c_role")
public class CRole extends BaseEntity {
    @Column(name="code",nullable = false,unique = true)
    private String code;

    @Column(name="description")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private Set<CUser> users = new LinkedHashSet<CUser>();
}
