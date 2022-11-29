package com.harulab.adapfit.domain.counseling.presentation.dto.res;

import com.harulab.adapfit.domain.counseling.domain.Message;
import com.harulab.adapfit.domain.counseling.domain.type.MessageType;
import com.harulab.adapfit.global.utils.CustomDateTimeFormatter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageResponseDto {

    private final String message;
    private final MessageType messageType;
    private final String username;
    private final Long userId;
    private final String sentAt;

    public static MessageResponseDto of(Message message) {
        return MessageResponseDto.builder()
                .message(message.getMessage())
                .messageType(message.getMessageType())
                .username(
                        message.getMessageType() == MessageType.USER ?
                                message.getUser().getNickname() : null)
                .userId(message.getMessageType() == MessageType.USER ?
                        message.getUser().getId() : null)
                .sentAt(CustomDateTimeFormatter.formatToDateTime(message.getCreatedAt()))
                .build();
    }

}
