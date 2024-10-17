package com.indentity_service.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id",columnDefinition = "VARCHAR(36)")
    private String id;

    @JsonIgnore
    @Version
    protected int version;

    @JsonIgnore
    @CreatedBy
    @Column(name="created_by")
    protected String createdBy;

    @JsonIgnore
    @CreatedDate
    @Column(name="created_at")
    protected LocalDateTime createAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name="last_modified_at")
    protected LocalDateTime lastModifiedAt;

    @JsonIgnore
    @LastModifiedBy
    @Column(name="last_modified_by")
    protected String lastModifiedBy;
}
