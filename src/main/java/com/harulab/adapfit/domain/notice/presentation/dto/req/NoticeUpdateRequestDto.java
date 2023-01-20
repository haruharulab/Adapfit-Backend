package com.harulab.adapfit.domain.notice.presentation.dto.req;

import com.harulab.adapfit.domain.log.global.utils.ValidMessageConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 21.
 */

@Getter
@NoArgsConstructor
public class NoticeUpdateRequestDto {

    @NotBlank(message = ValidMessageConstants.TITLE_NOT_BLANK)
    private String title;

    @NotBlank(message = ValidMessageConstants.CONTENT_NOT_BLANK)
    private String content;

    @Builder
    public NoticeUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
