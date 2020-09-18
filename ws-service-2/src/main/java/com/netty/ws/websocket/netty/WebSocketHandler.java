package com.netty.ws.websocket.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * WS服务
 *
 * @author DongXiaoQiang
 * @Date 2020/09/18
 */
@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("-----------" + ctx.channel().id() + "接收到信息：" + msg.text());
        ctx.writeAndFlush(new TextWebSocketFrame("netty：霍哗~~~~~~~~"));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("-----------增加连接channelActive：" + ctx.channel().id());
        ChannelHandlerPool.channelGroup.add(ctx.channel());
        ChannelHandlerPool.addChannel(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        closeChannel(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.info("-----------关闭连接exceptionCaught：" + ctx.channel().id());
        closeChannel(ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.info("---------------------read idle--------------------------" + ctx.channel().id());
                closeChannel(ctx.channel());
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.info("---------------------write idle--------------------------" + ctx.channel().id());
                ctx.writeAndFlush(new TextWebSocketFrame("KeepLive"));
            } else if (event.state() == IdleState.ALL_IDLE) {
                log.info("---------------------all idle--------------------------" + ctx.channel().id());
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    private void closeChannel(Channel channel) {
        log.info("-----------关闭连接channelInactive：" + channel.id());
        ChannelHandlerPool.channelGroup.remove(channel);
        ChannelHandlerPool.removeChannel(channel);
        if (channel != null) {
            channel.close();
        }
        channel.close();
    }
}
