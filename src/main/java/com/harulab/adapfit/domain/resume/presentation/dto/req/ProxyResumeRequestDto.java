package com.harulab.adapfit.domain.resume.presentation.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@NoArgsConstructor
@Getter
public class ProxyResumeRequestDto {

    @NotBlank(message = RECRUITMENT_ID_NOT_BLANK)
    private String recruitmentId;

    @NotNull(message = NAME_NOT_BLANK)
    private String name;

    @Email(message = INPUT_EMAIL_FORM)
    @NotBlank(message = EMAIL_NOT_BLANK)
    private String email;

    @NotBlank(message = PHONE_NUMBER_NOT_BLANK)
    private String phoneNumber;

    @Builder
    public ProxyResumeRequestDto(String recruitmentId, String name, String email, String phoneNumber) {
        this.recruitmentId = recruitmentId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
