package com.harulab.adapfit.domain.admin.domain;

import com.harulab.adapfit.domain.root.presentation.dto.req.UpdateAccountInfoRequestDto;
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

@DynamicUpdate
@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 32, unique = true)
    private String authId;

    @NotNull
    @Column(length = 256)
    private String password;

    @NotNull
    @Column(length = 8)
    private String nickname;

    @NotNull
    @Column(length = 32)
    private String email;

    @NotNull
    @Column(length = 12)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private Authority authority;

    @Column
    private String center;

    @Builder
    public Admin(String authId, String password, String nickname, String email, String phoneNumber, Authority authority, String role) {
        this.authId = authId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void matchedPassword(PasswordEncoder passwordEncoder, Admin admin, String password) {
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new AdapfitException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }

    public void updateInfo(UpdateAccountInfoRequestDto req) {
        if (!req.authIdIsNull()) {
            this.authId = req.getAuthId();
        }
        if (!req.emailIsNull()) {
            this.email = req.getEmail();
        }
        if (!req.nicknameIsNull()) {
            this.nickname = req.getNickname();
        }
        if (!req.phoneNumberIsNull()) {
            this.phoneNumber = req.getPhoneNumber();
        }
        if (!req.centerIsNull()) {
            this.center = req.getCenter();
        }
    }

    public void updatePassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }

    public void updateAuthority(String authority) {
        this.authority = Authority.valueOf(authority);
    }
}
