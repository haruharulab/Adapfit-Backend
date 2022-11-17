package com.harulab.adapfit.global.socket.property;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SocketProperty {

    public static final String MESSAGE_KEY = "message";
    public static final String ROOM_JOIN_KEY = "room-join";
    public static final String ROOM_LEAVE_KEY = "room-leave";
    public static final String SUBSCRIBE_KEY = "subscribe";
    public static final String ERROR = "error";

    public static final String USER_KEY = "user";
}
