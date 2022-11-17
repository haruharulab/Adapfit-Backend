package com.harulab.adapfit.global.socket.connect;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import com.harulab.adapfit.global.socket.property.ClientProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WebSocketJwtHandler {

    private final JwtAuth jwtAuth;

    public static final ConcurrentMap<String, SocketIOClient> socketIOClientMap = new ConcurrentHashMap<>();

    @OnConnect
    public void onConnect(SocketIOClient client) {
        String token = client.getHandshakeData().getHttpHeaders().get("Authorization");
        Authentication authentication = jwtAuth.authentication(token);
        socketIOClientMap.put(authentication.getName(), client);
        client.set(ClientProperty.USER_KEY, authentication.getName());
        log.info("Connected : " + client.getSessionId() + ", " + authentication.getName());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        socketIOClientMap.remove(client.get(ClientProperty.USER_KEY).toString());
        log.info("DisConnected : " + client.getSessionId());
    }

}
