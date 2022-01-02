package com.harulab.adapfit.domain.root.presentation.dto.req;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.admin.domain.type.Authority;
import com.harulab.adapfit.domain.root.exception.PasswordNotMatchException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 21.
 */

@Getter
@NoArgsConstructor
public class AdminCreateRequestDto {

    private String authId;
    private String email;
    private String nickname;
    private String password;
    private String validatePassword;
    private String phoneNumber;
    private String authority;

    @Builder
    public AdminCreateRequestDto(
            String authId,
            String email,
            String nickname,
            String password,
            String validatePassword,
            String phoneNumber,
            String authority
    ) {
        this.authId = authId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.validatePassword = validatePassword;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
        validatePassword();
    }

    public Admin toEntity() {
        return Admin.builder()
                .authId(authId)
                .email(email)
                .nickname(nickname)
                .password(password)
                .phoneNumber(phoneNumber)
                .authority(Authority.valueOf(authority))
                .build();
    }

    private void validatePassword() {
        if (!password.equals(validatePassword)) {
            throw new PasswordNotMatchException();
        }
    }
}
