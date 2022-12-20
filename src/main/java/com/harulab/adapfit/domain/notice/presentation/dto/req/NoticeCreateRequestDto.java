package com.harulab.adapfit.domain.notice.presentation.dto.req;

import com.harulab.adapfit.domain.notice.domain.Notice;
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
public class NoticeCreateRequestDto {

    private String title;
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
