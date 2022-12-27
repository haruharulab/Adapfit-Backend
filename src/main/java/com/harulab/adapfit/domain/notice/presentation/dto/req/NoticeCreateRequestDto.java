package com.harulab.adapfit.domain.notice.presentation.dto.req;

import com.harulab.adapfit.domain.notice.domain.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 21.
 */

@Getter
@NoArgsConstructor
public class NoticeCreateRequestDto {


    @NotBlank(message = TITLE_NOT_BLANK)
    private String title;

    @NotBlank(message = CONTENT_NOT_BLANK)
    private String content;

    @Builder
    public NoticeCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Notice toEntity() {
        return Notice.builder()
                .title(title)
                .content(content)
                .build();
    }
}
