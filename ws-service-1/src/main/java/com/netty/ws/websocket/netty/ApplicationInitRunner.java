package com.netty.ws.websocket.netty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Netty服务启动类
 *
 * @author DongXiaoQiang
 * @Date 2020/09/18
 */
@Component
@Slf4j
public class ApplicationInitRunner implements ApplicationRunner {

    @Autowired
    private NettyServer nettyServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        nettyServer.start();
        log.info("-----------------nettyServer启动成功---------------------");
    }
}
