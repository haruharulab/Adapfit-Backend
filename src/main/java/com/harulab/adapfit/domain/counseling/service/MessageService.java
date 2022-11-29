package com.harulab.adapfit.domain.counseling.service;

import com.corundumstudio.socketio.SocketIOServer;
import com.harulab.adapfit.domain.counseling.domain.Message;
import com.harulab.adapfit.domain.counseling.domain.Room;
import com.harulab.adapfit.domain.counseling.domain.repository.MessageRepository;
import com.harulab.adapfit.domain.counseling.facade.RoomFacade;
import com.harulab.adapfit.domain.counseling.presentation.dto.req.MessageRequestDto;
import com.harulab.adapfit.domain.counseling.presentation.dto.res.MessageResponseDto;
import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.facade.UserFacade;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.global.socket.property.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class MessageService {

    private final SocketIOServer server;
    private final UserFacade userFacade;
    private final RoomFacade roomFacade;
    private final MessageRepository messageRepository;


    public void sendSystemMessage(MessageRequestDto request) {
        sendMessage(request, null,
                roomFacade.findRoomById(Long.valueOf(request.getRoomId())));
    }

    @Transactional
    public void sendMessage(MessageRequestDto request, User user, Room room) {
        Message message = messageRepository.save(request.toEntity(user, room));
        server.getRoomOperations(request.getRoomId())
                .sendEvent(SocketProperty.MESSAGE_KEY, MessageResponseDto.of(message));
    }

}
