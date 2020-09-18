
### 简介
###### 1/基于netty底层网络服务实现websocket
###### 2/支持分布式channel
###### 3/支持分布式多节点websocket，通过gateway转发连接

###### 基本原理：将netty服务实现的websocket注册到eureka上，由网关统一维护连接并转发；

### 按顺序启动
###### `eureka-server（端口：8761）`，
###### `gateway（端口：8777）`，
###### `ws-service-1/ws-service-2（端口：8801/8803 支持多节点）（模拟多机器分布式节点）`


###### `eureka-server`是注册中心，
###### `gateway`、`ws-service`、`push-service`启动后都会注册到这。`eureka-server`提供了RESTAPI：`http://localhost:8761`获取当前`eureka-server`已注册的服务，在项目都启动好后，访问这个链接可以看到已经注册的实例。

###### `ws-service`应用为普通springboot web应用，提供了websocket测试客户端网页版，应用名为`push-service`，可以在浏览器中访问链接：`http://localhost:8777/push-service/`，这里是通过网关访问界面。

###### `ws-service`底层为netty实现，在启动`ws-service`应用时，同时也启动了配置的netty websocket服务，路径为`/websocket`，websocket应用注册到`eureka-server`的名称为`ws-service`，那么通过网关访问websocket服务的链接为`ws://localhost:8777/ws-service/websocket`

### 监控
###### http://localhost:8090/
