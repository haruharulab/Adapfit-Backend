package com.harulab.adapfit.domain.root.presentation.dto.req;

import com.harulab.adapfit.domain.root.domain.SuperAdmin;
import com.harulab.adapfit.domain.admin.domain.type.Authority;
import com.harulab.adapfit.domain.admin.exception.PasswordNotMatchException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@Getter
@NoArgsConstructor
public class SuperAdminCreateRequestDto {

    @NotBlank(message = AUTH_ID_NOT_BLANK)
    private String authId;

    @NotBlank(message = PASSWORD_ID_NOT_BLANK)
    private String password;

    @NotBlank(message = VALIDATE_PASSWORD_ID_NOT_BLANK)
    private String validatePassword;

    @NotBlank(message = EMAIL_NOT_BLANK)
    @Email(message = INPUT_EMAIL_FORM)
    private String email;

    @NotBlank(message = NICKNAME_NOT_BLANK)
    @Size(min = 3, max = 8, message = NICKNAME_SIZE)
    private String nickname;

    @Builder
    public SuperAdminCreateRequestDto(String authId, String password, String validatePassword, String email, String nickname) {
        this.authId = authId;
        this.password = password;
        this.validatePassword = validatePassword;
        this.email = email;
        this.nickname = nickname;
        validateIsMatchedPassword();
    }

    public SuperAdmin toEntity() {
        return SuperAdmin.builder()
                .authId(authId)
                .password(password)
                .email(email)
                .nickname(nickname)
                .authority(Authority.SUPER_ADMIN)
                .build();
    }

    private void validateIsMatchedPassword() {
        if (!Objects.equals(password, validatePassword)) {
            throw PasswordNotMatchException.EXCEPTION;
        }
    }
}
