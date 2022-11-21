package com.harulab.adapfit.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 12)
    private String authId;

    @NotNull
    @Column(length = 32)
    private String password;

    @NotNull
    @Column(length = 32)
    private String email;

    @NotNull
    @Column(length = 12)
    private Integer phoneNumber;

    @Builder
    public User(Long id, String authId, String password, String email, Integer phoneNumber) {
        this.id = id;
        this.authId = authId;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
