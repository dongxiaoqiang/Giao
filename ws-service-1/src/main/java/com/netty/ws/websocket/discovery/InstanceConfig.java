package com.netty.ws.websocket.discovery;

import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netty.ws.utils.BeanUtil;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.util.Set;

/**
 * 构建注册参数
 *
 * @author DongXiaoQiang
 * @Date 2020/09/18
 */
public class InstanceConfig extends MyDataCenterInstanceConfig {

    @Override
    public String getHostName(boolean refresh) {
        return BeanUtil.getBean(InstanceProperties.class).getHost();
    }

    @Override
    public int getNonSecurePort() {
        int tomcatPort;
        try {
            MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objectNames =
                    beanServer.queryNames(
                            new ObjectName("*:type=Connector,*"),
                            Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));

            tomcatPort = Integer.valueOf(objectNames.iterator().next().getKeyProperty("port"));
        } catch (Exception e) {
            return super.getNonSecurePort();
        }
        return tomcatPort;
    }
}
