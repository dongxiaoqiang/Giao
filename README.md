
### 简介
###### 1/基于netty底层网络服务实现websocket
###### 2/支持分布式channel
###### 3/支持分布式多节点websocket，通过gateway转发连接

###### 基本原理：将netty服务实现的websocket注册到eureka上，由网关统一维护连接并转发；

### 依次启动
###### `eureka-server注册中心`，
###### `admin服务监控`，
###### `gateway网关`，
###### `ws-service-1/ws-service-2（模拟多节点）`

######  测试客户端网页版，网关访问链接：http://localhost:8777/push-service/

######  通过网关访问websocket服务的链接为`ws://localhost:8777/ws-service/websocket`

### 服务监控
###### 地址：http://localhost:8090/
