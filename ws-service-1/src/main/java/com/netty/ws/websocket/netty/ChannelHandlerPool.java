package com.netty.ws.websocket.netty;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 维护channel通道
 *
 * @author DongXiaoQiang
 * @Date 2020/09/18
 */
@Slf4j
public class ChannelHandlerPool {

    public ChannelHandlerPool() {
    }

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 根据需求 channel可与用户绑定并维护
     */
    public static ConcurrentMap<String, Channel> channelMap = new ConcurrentHashMap<>();

    public static void addChannel(Channel channel) {
        channelMap.put(channel.id().asShortText(), channel);
    }

    public static void removeChannel(Channel channel) {
        channelMap.remove(channel.id().asShortText());
    }

    public static Channel getChannel(String id) {
        return channelMap.get(id);
    }
}
