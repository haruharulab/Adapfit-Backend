package com.harulab.adapfit.domain.admin.domain;

import com.harulab.adapfit.domain.admin.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.user.domain.type.Authority;
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
public class Admin {

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

    private String centerInfo;

    @NotNull
    @Column(length = 12)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private Authority authority;

    @Builder
    public Admin(String authId, String password, String email, String nickname, String centerInfo, String phoneNumber, Authority authority) {
        this.authId = authId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.centerInfo = centerInfo;
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
        this.email = req.getEmail();
        this.nickname = req.getNickname();
        this.centerInfo = req.getCenterInfo();
        this.phoneNumber = req.getPhoneNumber();
    }
}
