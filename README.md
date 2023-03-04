### 简介

* 1、基于netty底层网络服务实现websocket
* 2、集成服务监控
* 3、支持分布式多节点websocket，通过gateway转发连接
* 4、基本原理：将netty服务实现的websocket注册到eureka上，由网关统一维护连接并转发；

### 依次启动

* 1、注册中心：`eureka-server`，
* 2、服务监控：`admin`，
* 3、网关：`gateway`，
* 4、服务实例：`ws-service-1/ws-service-2`，`test-feign`

### 测试

* 1、网关测试客户端网页地址：http://localhost:8777/push-service/
* 2、网关直连websocket地址：ws://localhost:8777/ws-service/websocket
* 3、网关测试调用rpc服务：http://localhost:8777/push-service/v1/feign

### 服务监控

* 1、地址：http://localhost:8090/
* 2、账号：admin，密码：admin
