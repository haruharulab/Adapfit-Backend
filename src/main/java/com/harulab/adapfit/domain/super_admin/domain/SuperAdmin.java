package com.harulab.adapfit.domain.super_admin.domain;

import com.harulab.adapfit.domain.super_admin.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.user.domain.type.Authority;
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
@Table(name = "SUPER_ADMIN")
@Entity
public class SuperAdmin extends BaseTimeEntity {

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
    public SuperAdmin(String authId, String password, String email, String nickname, Authority authority) {
        this.authId = authId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.authority = authority;
    }

    public void matchedPassword(PasswordEncoder passwordEncoder, SuperAdmin superAdmin, String password) {
        if (!passwordEncoder.matches(password, superAdmin.getPassword())) {
            throw new AdapfitException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
