package com.harulab.adapfit.domain.resume.presentation.dto.req;

import com.harulab.adapfit.domain.log.global.utils.ValidMessageConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class ProxyResumeUpdateRequestDto {

    @NotBlank(message = ValidMessageConstants.APPLY_ID_NOT_BLANK)
    private String resumeId;
    private String name;

    private String email;

    private String phoneNumber;

    @Builder
    public ProxyResumeUpdateRequestDto(String resumeId, String name, String email, String phoneNumber) {
        this.resumeId = resumeId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
