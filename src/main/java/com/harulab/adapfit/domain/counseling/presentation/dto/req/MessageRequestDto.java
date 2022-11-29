package com.harulab.adapfit.domain.counseling.presentation.dto.req;

import com.harulab.adapfit.domain.counseling.domain.Message;
import com.harulab.adapfit.domain.counseling.domain.Room;
import com.harulab.adapfit.domain.counseling.domain.type.MessageType;
import com.harulab.adapfit.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDto {

    @NotNull
    private String message;

    @NotNull
    private MessageType messageType;

    @NotNull
    private String roomId;

    public Message toEntity(User user, Room room) {
        return Message.builder()
                .message(message)
                .messageType(messageType)
                .user(user)
                .room(room)
                .build();
    }

}
