package com.harulab.adapfit.global.socket.connect;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.harulab.adapfit.global.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocketConnectController {

    private final JwtUtil jwtUtil;

    @OnConnect
    public void onConnect(SocketIOClient client) {
        String token = client.getHandshakeData().getSingleUrlParam("authorization");
        client.set(SocketAuthenticationProperty.USER_KEY, jwtUtil.ExtractAuthIdFromToken(token));
    }
}