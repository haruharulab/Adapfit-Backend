package com.harulab.adapfit.global.socket.exception;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import com.harulab.adapfit.global.error.ErrorResponse;
import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;
import com.harulab.adapfit.global.socket.property.SocketProperty;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public class SocketExceptionListener implements ExceptionListener {
    @Override
    public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
        client.disconnect();
    }

    @Override
    public void onPingException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        return false;
    }

    private void runExceptionHandling(Exception e, SocketIOClient client) {
        final ErrorCode errorCode;

        if (e.getCause() instanceof AdapfitException) {
            errorCode = ((AdapfitException) e.getCause()).getErrorCode();
        } else {
            errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse message = new ErrorResponse(
                errorCode.getStatus(),
                errorCode.getCode(),
                errorCode.getMessage()
        );

        client.sendEvent(SocketProperty.ERROR, message);
    }

}

