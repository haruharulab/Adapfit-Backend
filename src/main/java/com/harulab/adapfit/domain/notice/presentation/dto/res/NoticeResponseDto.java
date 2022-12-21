package com.harulab.adapfit.domain.notice.presentation.dto.res;

import com.harulab.adapfit.domain.notice.domain.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 21.
 */

@Getter
public class NoticeResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public NoticeResponseDto(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdAt = notice.getCreatedAt();
        this.updatedAt = notice.getModifiedAt();
    }
}
