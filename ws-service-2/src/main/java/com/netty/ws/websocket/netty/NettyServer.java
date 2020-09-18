package com.netty.ws.websocket.netty;

import com.netty.ws.websocket.discovery.InstanceProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Netty服务
 *
 * @author DongXiaoQiang
 * @Date 2020/09/18
 */
@Slf4j
@Component
public class NettyServer {

    @Autowired
    private InstanceProperties instanceProperties;

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            sb.group(group, bossGroup) // 绑定线程池
                    .channel(NioServerSocketChannel.class) // 指定使用的channel
                    .localAddress(instanceProperties.getPort()) // 绑定监听端口
                    .childHandler(
                            new ChannelInitializer<SocketChannel>() { // 绑定客户端连接时候触发操作

                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {
                                    log.info("收到新连接");
                                    // websocket协议本身是基于http协议的，所以这边也要使用http解编码器
                                    ch.pipeline().addLast(new HttpServerCodec());
                                    // 以块的方式来写的处理器
                                    ch.pipeline().addLast(new ChunkedWriteHandler());
                                    ch.pipeline().addLast(new HttpObjectAggregator(65536));
                                    ch.pipeline()
                                            .addLast(
                                                    new WebSocketServerProtocolHandler(
                                                            instanceProperties.getPath(), null, true, 65536 * 10));
                                    ch.pipeline().addLast(new IdleStateHandler(30, 20, 0));
                                    // ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                                    ch.pipeline().addLast(new WebSocketHandler());
                                }
                            });
            sb.childOption(ChannelOption.TCP_NODELAY, true);

            ChannelFuture cf = sb.bind().sync(); // 服务器异步创建绑定
            log.info(NettyServer.class + " 启动正在监听： " + cf.channel().localAddress());
            cf.channel().closeFuture().sync(); // 关闭服务器通道
        } finally {
            group.shutdownGracefully(); // 释放线程池资源
            bossGroup.shutdownGracefully();
        }
    }
}
