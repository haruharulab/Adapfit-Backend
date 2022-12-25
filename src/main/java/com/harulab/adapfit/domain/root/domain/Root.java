package com.harulab.adapfit.domain.root.domain;

import com.harulab.adapfit.domain.admin.domain.type.Authority;
import com.harulab.adapfit.global.entity.BaseTimeEntity;
import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@Entity
public class Root extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 32, unique = true)
    private String authId;

    @NotNull
    @Column(length = 256)
    private String password;

    @NotNull
    @Column(length = 32)
    private String email;

    @NotNull
    @Column(length = 8)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private Authority authority;

    @Builder
    public Root(String authId, String password, String email, String nickname, Authority authority) {
        this.authId = authId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.authority = authority;
    }

    public void matchedPassword(PasswordEncoder passwordEncoder, Root root, String password) {
        if (!passwordEncoder.matches(password, root.getPassword())) {
            throw new AdapfitException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
