package com.harulab.adapfit.domain.user.domain;

import com.harulab.adapfit.domain.user.domain.type.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 32)
    private String authId;

    @NotNull
    @Column(length = 32)
    private String password;

    @NotNull
    @Column(length = 32)
    private String email;

    @NotNull
    @Column(length = 12)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 6)
    private Authority authority;

    @Builder
    public User(Long id, String authId, String password, String email, String phoneNumber) {
        this.id = id;
        this.authId = authId;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
