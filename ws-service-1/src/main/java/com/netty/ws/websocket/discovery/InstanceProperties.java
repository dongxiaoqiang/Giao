package com.netty.ws.websocket.discovery;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * WS配置参数
 *
 * @author DongXiaoQiang
 * @Date 2020/09/18
 */
@Component
@Data
@ConfigurationProperties(InstanceProperties.PREFIX)
public class InstanceProperties {

    public static final String PREFIX = "netty-websocket.discovery.client";

    private String host;
    private Integer port;
    private String name;
    private String path;
}
