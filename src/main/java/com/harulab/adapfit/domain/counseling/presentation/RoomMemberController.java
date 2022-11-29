package com.harulab.adapfit.domain.counseling.presentation;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.harulab.adapfit.domain.counseling.presentation.dto.req.RoomRequestDto;
import com.harulab.adapfit.domain.counseling.service.RoomMemberService;
import com.harulab.adapfit.global.socket.property.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class RoomMemberController {

    private final RoomMemberService roomMemberService;

    @OnEvent(SocketProperty.ROOM_JOIN_KEY)
    public void joinRoom(
            SocketIOClient client,
            @RequestBody @Valid RoomRequestDto request
    ) {
        System.out.println("execute");
        roomMemberService.joinRoom(client, request);
    }
}
