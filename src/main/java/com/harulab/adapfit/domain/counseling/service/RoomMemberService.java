package com.harulab.adapfit.domain.counseling.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.harulab.adapfit.domain.counseling.domain.Room;
import com.harulab.adapfit.domain.counseling.domain.RoomMember;
import com.harulab.adapfit.domain.counseling.domain.repository.RoomMemberRepository;
import com.harulab.adapfit.domain.counseling.domain.type.MessageType;
import com.harulab.adapfit.domain.counseling.facade.RoomFacade;
import com.harulab.adapfit.domain.counseling.presentation.dto.req.MessageRequestDto;
import com.harulab.adapfit.domain.counseling.presentation.dto.req.RoomRequestDto;
import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.facade.UserFacade;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class RoomMemberService {

    private final MessageService messageService;
    private final RoomFacade roomFacade;
    private final RoomMemberRepository roomMemberRepository;
    private final UserFacade userFacade;

    @Transactional
    public void joinRoom(SocketIOClient client, RoomRequestDto req) {
        joinRoom(client, roomFacade.findRoomById(Long.valueOf(req.getRoomId())), userFacade.findUserByClient(client));
    }

    @Transactional
    public void joinRoom(SocketIOClient client, Room room, User user) {
        client.joinRoom(String.valueOf(room.getId()));
        messageService.sendSystemMessage(new MessageRequestDto(
                user.getNickname() + " 님이 참가했습니다.", MessageType.SYSTEM, String.valueOf(room.getId())));

        roomMemberRepository.save(
                RoomMember.builder()
                        .room(room)
                        .user(user)
                        .build()
        );
    }
}
