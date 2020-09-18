package com.netty.ws.send;

import com.netty.ws.websocket.netty.ChannelHandlerPool;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * 定时任务 模拟推送
 *
 * @author DongXiaoQiang
 * @Date 2020/09/18
 */
@Configuration
@EnableScheduling
public class SendMessage {
    private int times = 0;

    /**
     * 模拟推送
     */
    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() {
        for (Channel channel : ChannelHandlerPool.channelMap.values()) {
            channel.writeAndFlush(
                    new TextWebSocketFrame(
                            "netty：一给我哩Giao Giao (来自服务器推送)! 第" + times++ + "次，时间：" + LocalDateTime.now()));
        }
    }
}
