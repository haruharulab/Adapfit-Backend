package com.harulab.adapfit.domain.user.presentation.dto.req;

import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.domain.type.Authority;
import com.harulab.adapfit.domain.user.exception.PasswordNotMatchException;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@Getter
public class UserRequestDto {

    @NotBlank(message = AUTH_ID_NOT_BLANK)
    private final String authId;

    @NotBlank(message = PASSWORD_ID_NOT_BLANK)
    private final String password;

    @NotBlank(message = VALIDATE_PASSWORD_ID_NOT_BLANK)
    private final String validatePassword;

    @NotBlank(message = EMAIL_NOT_BLANK)
    @Email(message = INPUT_EMAIL_FORM)
    private final String email;

    @NotBlank(message = NICKNAME_NOT_BLANK)
    @Size(min = 3, max = 8, message = NICKNAME_SIZE)
    private final String nickname;

    @NotBlank(message = PHONE_NUMBER_NOT_BLANK)
    private final String phoneNumber;

    @Builder
    public UserRequestDto(String authId, String password, String validatePassword, String email, String nickname, String phoneNumber) {
        this.authId = authId;
        this.password = password;
        this.validatePassword = validatePassword;
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        validateIsMatchedPassword();
    }

    public User toEntity() {
        return User.builder()
                .authId(authId)
                .email(email)
                .nickname(nickname)
                .password(password)
                .phoneNumber(phoneNumber)
                .authority(Authority.ADMIN)
                .build();
    }

    private void validateIsMatchedPassword() {
        if (!Objects.equals(password, validatePassword)) {
            throw PasswordNotMatchException.EXCEPTION;
        }
    }
}
