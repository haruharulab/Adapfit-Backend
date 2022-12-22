package com.harulab.adapfit.domain.notice.presentation.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 22.
 */

@Getter
@NoArgsConstructor
public class MessageRequestDto {

    private Long senderId;
    private Long receiverId;
    private String message;

    @Builder
    public MessageRequestDto(Long senderId, Long receiverId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
    }
}
