package com.indentity_service.security.model;

import com.indentity_service.user.model.CUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "c_refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id",columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name="refresh_token",nullable = false,unique = true)
    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CUser user;
}
