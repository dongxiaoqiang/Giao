package com.netty.ws.websocket.discovery;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.LeaseInfo;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 手动注册websocket至微服务注册中心
 *
 * @author DongXiaoQiang
 * @Date 2020/09/18
 */
@Component
public class WebsocketEurekaClient implements SmartInitializingSingleton {

    @Value("${eureka.client.serviceUrl.defaultZone}")
    private String defaultZone;
    @Autowired
    private EurekaInstanceConfig config;
    @Autowired
    private InstanceProperties instanceProperties;

    @Override
    public void afterSingletonsInstantiated() {
        EurekaClientConfigBean eurekaClientConfigBean = new EurekaClientConfigBean();
        eurekaClientConfigBean.setServiceUrl(
                new HashMap<String, String>() {
                    {
                        put("defaultZone", defaultZone);
                    }
                });

        new DiscoveryClient(
                new ApplicationInfoManager(new InstanceConfig(), create(config)),
                eurekaClientConfigBean);
    }

    private InstanceInfo create(EurekaInstanceConfig config) {
        LeaseInfo.Builder leaseInfoBuilder =
                LeaseInfo.Builder.newBuilder()
                        .setRenewalIntervalInSecs(config.getLeaseRenewalIntervalInSeconds())
                        .setDurationInSecs(config.getLeaseExpirationDurationInSeconds());
        InstanceInfo.Builder builder = InstanceInfo.Builder.newBuilder();

        String namespace = config.getNamespace();
        if (!namespace.endsWith(".")) {
            namespace = namespace + ".";
        }
        builder
                .setNamespace(namespace)
                .setAppName(instanceProperties.getName())
                .setInstanceId(
                        String.join(
                                ":",
                                instanceProperties.getHost(),
                                instanceProperties.getName(),
                                String.valueOf(instanceProperties.getPort())))
                .setAppGroupName(config.getAppGroupName())
                .setDataCenterInfo(config.getDataCenterInfo())
                .setIPAddr(instanceProperties.getHost())
                .setHostName(instanceProperties.getHost())
                .setPort(instanceProperties.getPort())
                .enablePort(InstanceInfo.PortType.UNSECURE, config.isNonSecurePortEnabled())
                .setSecurePort(config.getSecurePort())
                .enablePort(InstanceInfo.PortType.SECURE, config.getSecurePortEnabled())
                .setVIPAddress(instanceProperties.getName())
                .setSecureVIPAddress(instanceProperties.getName())
                .setHomePageUrl("/", null)
                .setStatusPageUrl(config.getStatusPageUrlPath(), config.getStatusPageUrl())
                .setHealthCheckUrls(
                        config.getHealthCheckUrlPath(),
                        config.getHealthCheckUrl(),
                        config.getSecureHealthCheckUrl())
                .setASGName(config.getASGName());
        builder.setStatus(InstanceInfo.InstanceStatus.UP);

        for (Map.Entry<String, String> mapEntry : config.getMetadataMap().entrySet()) {
            String key = mapEntry.getKey();
            String value = mapEntry.getValue();
            if (value != null && !value.isEmpty()) {
                builder.add(key, value);
            }
        }

        InstanceInfo instanceInfo = builder.build();
        instanceInfo.setLeaseInfo(leaseInfoBuilder.build());
        return instanceInfo;
    }
}
