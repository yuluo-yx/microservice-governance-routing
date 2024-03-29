# Label Routing Docker-Compose Quickstart

本文章将会说明如何使用 Docker 来完成 Spring-Cloud-Alibaba (以下简称 SCA) 服务治理功能中标签路由功能的使用示例。

## 1. 环境准备

### 1.1 准备服务 Jar 包

1. 进入 `governance-routing-examples` 目录下，执行 `mvn clean package` 打包 example 服务 jar 包；(打包结果如下所示)

   ```java
   [INFO] ------------------------------------------------------------------------
   [INFO] Reactor Summary for MicroService Governance Routing 2023.10.14:
   [INFO]
   [INFO] MicroService Governance Routing Dependencies ....... SUCCESS [  0.215 s]
   [INFO] MicroService Governance Routing .................... SUCCESS [  1.009 s]
   [INFO] MicroService Governance Routing Docs ............... SUCCESS [  0.043 s]
   [INFO] MicroService Governance Routing Starters ........... SUCCESS [  0.045 s]
   [INFO] MicroService Governance Routing Starter ............ SUCCESS [  4.792 s]
   [INFO] MicroService Governance Routing Gateway Adapter .... SUCCESS [  1.748 s]
   [INFO] MicroService Governance Routing Examples ........... SUCCESS [  0.026 s]
   [INFO] Governance Routing Example Consumer Common Module .. SUCCESS [  0.264 s]
   [INFO] Governance Routing Example Gateway Consumer Example Module SUCCESS [  1.145 s]
   [INFO] MicroService Governance Routing Zuul Adapter ....... SUCCESS [  1.520 s]
   [INFO] Label Routing Zuul Consumer Example ................ SUCCESS [  0.740 s]
   [INFO] Governance Routing Example Routing Service Provider Module SUCCESS [  0.900 s]
   [INFO] MicroService Governance Routing Service Adapter .... SUCCESS [  1.009 s]
   [INFO] Governance Routing Example WebClient Consumer Example Module SUCCESS [  0.063 s]
   [INFO] Label Routing OpenFeign Consumer Example ........... SUCCESS [  0.748 s]
   [INFO] Label Routing RestTemplate Consumer Example ........ SUCCESS [  0.744 s]
   [INFO] Label Routing WebClient Consumer Example ........... SUCCESS [  0.764 s]
   [INFO] Governance Routing Istio Label Routing Consumer Example SUCCESS [  0.871 s]
   [INFO] MicroService Governance Routing Common ............. SUCCESS [  2.291 s]
   [INFO] MicroService Governance Routing Xds Adapter ........ SUCCESS [  2.415 s]
   [INFO] MicroService Governance Routing Docker ............. SUCCESS [  0.042 s]
   [INFO] ------------------------------------------------------------------------
   [INFO] BUILD SUCCESS
   [INFO] ------------------------------------------------------------------------
   [INFO] Total time:  21.855 s
   [INFO] Finished at: 2023-11-04T11:27:39+08:00
   [INFO] ------------------------------------------------------------------------
   ```

2. 进入 `governance-routing-docker/src/main` 目录下，执行以下脚本移动 jar 包到指定目录下；

   ```shell
   # linux/mac 直接执行
   ./move-example-jar.sh
   
   # windows 下使用 github bash 执行
   ,/move-example-jar.sh
   ```

3. 分别进入 `label-routing-gateway-consumer-example` `label-routing-service-provider-example` `label-routing-webClient-consumer-example`（label-routing-quickstart 子级目录） 目录下查看 `app.jar` 是否存在。如果不存在手动移动即可；

4. 检查 `application-docker.yml` 配置文件是否准确。

### 1.2 安装 Docker

如果本机机器中没有 Docker 和 Docker-Compose 请参照如下链接进行安装

- Docker：https://docs.docker.com/engine/install/
- Docker-Compose：https://docs.docker.com/compose/install/

### 1.3 目录说明

```markdown
└─governance-routing-docker
    │  .env													# Docker env 配置文件
    │  .gitignore							
    │  docker-compose-web-client-consume.yml				# 客户端消费者 example
    │  docker-compose-service-example.yml					# 服务提供者
    │  docker-compose-gateway-consumer.yml					# 服务提供者
    │  move-jar.sh											# 移动 jar 包脚本
    │  README-zh.md											# 参考 README 文件
    ├─--Nacos												# 注册中心
    ├─--Portainer											# 容器监控服务
	├─--apps
        ├─gateway-consumer-example
        │  │  DockerFile									# 启动服务消费者的 DockerFile 文件
        │  │
        │  ├─gateway-consumer-example
        │  │      app.jar									# spring boot 应用 jar 包
        │  │      application-docker.yml					# spring boot 应用在 docker 容器中启动时的配置文件
        │  │
        │  └─zuul-consumer-example
        │          app.jar
        │          application-docker.yml
        │
        ├─service-provider-example							# 服务提供者文件夹
        │      app.jar
        │      application-dockerA1.yml
        │      application-dockerA2.yml
        │      application-dockerA3.yml
        │      application-dockerA4.yml
        │      DockerFile
        │      start-service-provider.sh					# 服务提供者启动脚本（docker内）
        │
        └-web-client-consumer-example						# 三种请求客户端消费者文件夹
            │  DockerFile
            │
            ├─feign-consumer-example
            │      app.jar
            │      application-docker.yml
            │
            ├─reactive-consumer-example
            │      app.jar
            │      application-docker.yml
            │
            └─rest-consumer-example
                    app.jar
                    application-docker.yml
```

### 1.4 Postman 测试脚本

1. 进入 `governance-routing-examples/resources` 文件夹下，分别将客户端消费者和网关消费者请求脚本导入 postman 中；

## 2. 启动服务提供者和 nacos-server

0. 分别进入 `governance-routing-examples/src/main/nacos` 和 `/portainer` 文件夹下，输入以下命令启动 nacos-server 和portainer

   ```shell
   docker-compose up -d 
   ```

1. 进入 `governance-routing-examples/src/main` 文件夹下，在 terminal 中输入以下命令构建并启动 service-provider-example 容器

   ```shell
   # 构建 docker 镜像
   docker-compose -f docker-compose-service-provider.yml build
   
   # 启动 docker 容器
   docker-compose -f docker-compose-service-provider.yml up -d 
   
   # 可以去掉最后的 -d 参数，查看服务启动过程中的日志输出
   docker-compose -f docker-compose-service-provider.yml up
   ```

2. 间隔一段时间之后（需要等待 docker 容器启动完成）本地机器访问 nacos 的控制台地址 (http://127.0.0.1:8848/nacos) 查看已经注册上线的 service-provider，其中包含四个服务实例。



   <img src="./assets/img/nacos-service-provider.png" style="zoom: 80%;" >



3. 如果出现以上服务注册信息，即证明 docker 容器启动成功！点击详情可以看到服务提供者实例的元数据信息。



   <img src="./assets/img/service-provider-metadata.png" style="zoom: 80%;" >



## 3. 客户端服务消费者示例

### 3.1 启动服务消费者容器

1. 进入 `governance-routing-docker/src/main` 文件夹下，执行以下命令启动请求客户端 example 应用容器；

   ```shell
   # 构建 docker 镜像
   docker-compose -f docker-compose-web-client-consumer.yml build
   
   # 启动 docker 容器
   docker-compose -f docker-compose-web-client-consumer.yml up -d 
   
   # 可以去掉最后的 -d 参数，查看服务启动过程中的日志输出
   docker-compose -f docker-compose-web-client-consumer.yml up
   ```

2. 去往 nacos 查看服务注册情况，等待服务上线之后，出现以下信息证明容器启动成功；

   <img src="./assets/img/nacos-webClient-consumer.jpg" style="zoom: 80%;" >

3. 进入 postman 中，发起请求，查看服务之间是否可以正常通信**（不添加任务路由规则，只依靠 ribbon 进行负载均衡）**；

   分别进入 `feign` `rest` `webcleint` 文件中点击 `v1版本测试` 和 `v2版本测试` 在不添加路由配置信息时发送请求，查看四种服务提供者是否均可以被消费者正常消费

   <img src="./assets/img/postman-webclient-consumer.jpg" style="zoom:67%;" >

​	分别点击查看响应值是否四种实例均被调用到，确认成功之后进入下一步。**（默认的 ribbon 负载均衡规则为 ZoneAvoidanceRule）**

### 3.2 发布路由规则测试标签路由

> 以 feign 客户端调用为例演示，Rest 和 WebClient 相同

#### 3.2.1 发布路由规则

> **确保四个服务提供者均能被消费者正常调用！**

服务消费者（openfeign-consumer-example，restTemplate-consumer-example，webClient-consumer-example）实例中设置的路由规则如下：

```json
[
  {
    "targetService": "routing-service-provider",
    "labelRouteRule": {
      "matchRouteList": [
        {
          "ruleList": [
            {
              "type": "header",
              "condition": "=",
              "key": "tag",
              "value": "v2"
            },
            {
              "type": "parameter",
              "condition": ">",
              "key": "id",
              "value": "10"
            },
            {
              "type": "path",
              "condition": "=",
              "value": "/router-test",
              "key": null
            }
          ],
          "version": "v2",
          "weight": 100,
          "fallback": null
        }
      ],
      "defaultRouteVersion": "v1"
    }
  }
]
```

代码对应的路由规则如下：

> 若同时满足请求参数中含有`tag=v2`，请求头中含有 id 且值小于10，uri 为`/router-test`则流量全部路由到 v2 版本中，若有一条不满足，则流量路由到 v1 版本中。

规则也支持动态修改，测试动态修改的规则如下：

```json 
[
   {
      "targetService": "routing-service-provider",
      "labelRouteRule": {
         "matchRouteList": [
            {
               "ruleList": [
                  {
                     "type": "header",
                     "condition": "=",
                     "key": "tag",
                     "value": "v2"
                  },
                  {
                     "type": "parameter",
                     "condition": ">",
                     "key": "id",
                     "value": "10"
                  },
                  {
                     "type": "path",
                     "condition": "=",
                     "value": "/router-test",
                     "key": null
                  }
               ],
               "version": "v2",
               "weight": 50,
               "fallback": null
            }
         ],
         "defaultRouteVersion": "v1"
      }
   }
]
```
代码对应的规则如下：

> 若同时满足请求参数中含有`tag=v2`，请 求头中含有 id 且值小于10，uri 为`/router-test`，则 50% 流量路由到 v2 版本中，剩下的流量路由到 v1 版本中，若有一条不满足，则流量路由到 v1 版本中。

区域亲和性路由规则如下：

```yml
    # label routing configuration
    governance:
      routing:
        region: dev
        zone: zone1
      # rule: RandomRule
```

> 当服务实例满足所设置的 `region=dev`, `zone=zone1` 规则时，路由到指定服务实例。

#### 3.2.2 区域亲和性路由规则存在时

1. 添加路由规则

   ```shell
   # 预期结果：
   # v1：Route in 172.18.0.3:18083, region: dev, zone: zone1, version: v1
   # v2：观察 service-provider 的元数据发现，没有 region=dev，zone=zone1，version=v2 的服务实例，因此区域亲和性路由会退化为标签路由效果，预期为以下结果：
   # 	 Route in 172.18.0.3:18082, region: qa, zone: zone2, version: v2
   #	 Route in 172.18.0.3:18084, region: dev, zone: zone2, version: v2
   
   # 测试发现和预期结果匹配！
   ```

2. 更新路由规则

   ```shell
   # 预期结果：
   # v1：Route in 172.18.0.3:18083, region: dev, zone: zone1, version: v1
   # v2：因为设置了区域亲和性路由规则，所以即使 v1 和 v2 版本各自 50 的权重，但是还是会根据区域亲和性路由规则选取服务实例, 预期结果为：
   # 	 Route in 192.168.2.9:18083, region: dev, zone: zone1, version: v1
   
   # 测试发现和预期结果匹配！
   ```

#### 3.2.3 区域亲和性路由不存在时

> Note: 因为 docker 镜像有缓存性，可能注释掉之后重新打包存在不生效的情况，重启 docker 服务再次尝试！

为了验证此场景，需要更改配置文件，取消区域亲和性路由配置。**执行 `docker-compose -f docker-compose-web-client-consumer.yml stop` 停止消费者容器。**

在 `governance-routing-docker/src/main/apps/web-client-consumer-example/feign-consumer-example/application-docker.yml` 文件中，注释区域亲和性路由配置：（Rest 和 WebClient 消费者同理）

```properties
    # label routing configuration
    governance:
      routing:
        # region: dev
        # zone: zone1
      # rule: RandomRule
```

重新 **构建** 和 **启动** 容器。

1. 添加路由规则

   ```shell
   # 预期结果：
   # v1：因为没有区域亲和性路由限制，所以会在实例之间按照 ribbon 的规则进行负载均衡
   #	Route in 172.18.0.3:19093, region: dev, zone: zone1, version: v1
   #	Route in 172.18.0.3:19091, region: qa, zone: zone1, version: v1
   
   # v2：因为没有区域亲和性路由限制，所以会在实例之间按照 ribbon 的规则进行负载均衡
   #	Route in 172.18.0.3:19094, region: qa, zone: zone2, version: v2
   #	Route in 172.18.0.3:19092, region: dev, zone: zone2, version: v2
   
   # 测试发现，符合预期结果
   ```

2. 更新路由规则

   ```properties
   # 预期结果
   # v1：因为没有区域亲和性路由限制，路由结果按照标签路由选择服务实例，所以会在两个实例之间按照 ribbon 的规则进行负载均衡
   #	Route in 172.18.0.3:19091, region: qa, zone: zone1, version: v1
   # 	Route in 172.18.0.3:19093, region: dev, zone: zone1, version: v1
     
   # v2：v1 和 v2 权重各占 50%，所以四种服务实例的调用结果都会出现
   #	Route in 172.18.0.3:19092, region: qa, zone: zone2, version: v2
   #	Route in 172.18.0.3:19094, region: dev, zone: zone2, version: v2
   #	Route in 172.18.0.3:19091, region: qa, zone: zone1, version: v1
   #	Route in 172.18.0.3:19093, region: dev, zone: zone1, version: v1
   
   # 测试发现，符合预期结果
   ```

### 3.3 关闭容器

执行以下命令关闭服务消费者容器:

```shell
docker-compose -f docker-compose-web-client-consumer.yml stop
```

## 4. 网关服务消费者示例

### 4.1 启动网关服务消费者容器

1. 进入 `governance-routing-docker/src/main` 文件夹下，执行以下命令启动网关客户端 example 应用容器；

   ```shell
   # 构建 docker 镜像
   docker-compose -f docker-compose-gateway-consumer.yml build
   
   # 启动 docker 容器
   docker-compose -f docker-compose-gateway-consumer.yml up -d 
   
   # 可以去掉最后的 -d 参数，查看服务启动过程中的日志输出
   docker-compose -f docker-compose-gateway-consumer.yml up
   ```

2. 去往 nacos 查看服务注册情况，等待服务上线之后，出现以下信息证明容器启动成功：


   <img src="./assets/img/nacos-gateway-consumer.png">


3. 进入 postman 中，发起请求，查看服务之间是否可以正常通信**（不添加任务路由规则，只依靠 ribbon 进行负载均衡）**；


分别进入 `sc-gw` `zuul` 文件中点击 `v1版本测试` 和 `v2版本测试` 在不添加路由配置信息时发送请求，查看四种服务提供者是否均可以被消费者正常消费


   <img src="./assets/img/postman-gateway-consumer.png" style="zoom:67%;" >


​	分别点击请求查看响应值，服务提供者的四个实例均被调用到，确认成功之后进入下一步。**（默认的 ribbon 负载均衡规则为 RandomRule ）**

### 4.2 发布路由规则测试标签路由

> **以 gateway 为例，zuul 网关相同！**

#### 4.1.1 发布路由规则

网关消费者中的标签路由规则如下：

```json
[
   {
      "targetService": "routing-service-provider",
      "labelRouteRule": {
         "matchRouteList": [
            {
               "ruleList": [
                  {
                     "type": "header",
                     "condition": "=",
                     "key": "tag",
                     "value": "v2"
                  },
                  {
                     "type": "parameter",
                     "condition": ">",
                     "key": "id",
                     "value": "10"
                  },
                  {
                     "type": "path",
                     "condition": "=",
                     "value": "/test-a1",
                     "key": null
                  }
               ],
               "version": "v2",
               "weight": 100,
               "fallback": null
            }
         ],
         "defaultRouteVersion": "v1"
      }
   }
]
```

代码对应的路由规则如下：

> 若同时满足请求参数中含有`tag=v2`，请求头中含有 id 且值小于10，uri 为`/test-a1`则流量全部路由到 v2 版本中，若有一条不满足，则流量路由到 v1 版本中。

更新路由规则：

```json
[
   {
      "targetService": "routing-service-provider",
      "labelRouteRule": {
         "matchRouteList": [
            {
               "ruleList": [
                  {
                     "type": "header",
                     "condition": "=",
                     "key": "tag",
                     "value": "v2"
                  },
                  {
                     "type": "parameter",
                     "condition": ">",
                     "key": "id",
                     "value": "10"
                  },
                  {
                     "type": "path",
                     "condition": "=",
                     "value": "/test-a1",
                     "key": null
                  }
               ],
               "version": "v2",
               "weight": 50,
               "fallback": null
            }
         ],
         "defaultRouteVersion": "v1"
      }
   }
]

```

代码对应的规则如下：

> 若同时满足请求参数中含有`tag=v2`，请 求头中含有 id 且值小于10，uri 为`/test-a1`，则 50% 流量路由到 v2 版本中，剩下的流量路由到 v1 版本中，若有一条不满足，则流量路由到 v1 版本中。

区域亲和性路由规则如下：

```yml
    # label routing configuration
    governance:
      routing:
        region: dev
        zone: zone1
      # rule: RandomRule
```

> 当服务实例满足所设置的 `region=dev`, `zone=zone1` 规则时，路由到指定服务实例。

#### 4.1.2 区域亲和性路由规则存在时

1. 添加路由规则

   ```shell
   # 预期结果：
   # v1：Route in 172.18.0.3:18083, region: dev, zone: zone1, version: v1
   # v2：观察 service-provider 的元数据发现，没有 region=dev，zone=zone1，version=v2 的服务实例，因此区域亲和性路由会退化为标签路由效果，预期为以下结果：
   # 	 Route in 172.18.0.3:19092, region: qa, zone: zone2, version: v2
   #	 Route in 172.18.0.3:19094, region: dev, zone: zone2, version: v2
   
   # 测试发现和预期结果匹配！
   ```

2. 更新路由规则

   ```shell
   # 预期结果：
   # v1：Route in 172.18.0.3:19093, region: dev, zone: zone1, version: v1
   # v2：因为设置了区域亲和性路由规则，所以即使 v1 和 v2 版本各自 50 的权重，但是还是会根据区域亲和性路由规则选取服务实例, 预期结果为：
   # 	 Route in 172.18.0.3:19093, region: dev, zone: zone1, version: v1
   
   # 测试发现和预期结果匹配！
   ```

#### 4.1.3 区域亲和性路由不存在时

> Note: 因为 docker 镜像有缓存性，可能注释掉之后存在不生效的情况，重启 docker 服务再次尝试！

为了验证此场景，需要更改配置文件，取消区域亲和性路由配置。**执行 `docker-compose -f docker-compose-gateway-consumer.yml stop` 停止消费者容器。**

在 `governance-routing-docker/src/main/apps/gateway-consumer-example/gateway-consumer-example/application-docker.yml` 文件中，注释区域亲和性路由配置：（Zuul 网关消费者同理）

```properties
    # Regional affinity routing configuration
    governance:
      routing:
        # region: dev
        # zone: zone1
        # rule: RandomRule
```

重新 **构建** 和 **启动** 容器。

1. 添加路由规则

   ```shell
   # 预期结果：
   # v1：因为没有区域亲和性路由限制，所以会在实例之间按照 ribbon 的规则进行负载均衡
   #	Route in 172.18.0.3:19093, region: dev, zone: zone1, version: v1
   #	Route in 172.18.0.3:19091, region: qa, zone: zone1, version: v1
   
   # v2：因为没有区域亲和性路由限制，所以会在实例之间按照 ribbon 的规则进行负载均衡
   #	Route in 172.18.0.3:19094, region: qa, zone: zone2, version: v2
   #	Route in 172.18.0.3:19092, region: dev, zone: zone2, version: v2
   
   # 测试发现，符合预期结果
   ```

2. 更新路由规则

   ```properties
   # 预期结果
   # v1：因为没有区域亲和性路由限制，路由结果按照标签路由选择服务实例，所以会在两个实例之间按照 ribbon 的规则进行负载均衡
   #	Route in 172.18.0.3:19091, region: qa, zone: zone1, version: v1
   # 	Route in 172.18.0.3:19093, region: dev, zone: zone1, version: v1
     
   # v2：v1 和 v2 权重各占 50，所以四种服务实例的调用结果都会出现
   #	Route in 172.18.0.3:19092, region: qa, zone: zone2, version: v2
   #	Route in 172.18.0.3:19094, region: dev, zone: zone2, version: v2
   #	Route in 172.18.0.3:19091, region: qa, zone: zone1, version: v1
   #	Route in 172.18.0.3:19093, region: dev, zone: zone1, version: v1
   
   # 测试发现，符合预期结果
   ```

### 4.3  关闭容器

执行以下命令关闭服务消费者容器:

```shell
docker-compose -f docker-compose-gateway-consumer.yml stop
```

## 5. 服务容器移除

1. 执行以下命令停止正在运行的服务提供者和 nacos server 容器

   ```shell
   docker-compose -f docker-compose-service-provider.yml stop
   ```

2. 执行以下命令删除所有容器和镜像

   ```shell
   # 按条件筛选之后删除镜像
   docker rmi `docker images | grep sca-routing-** | awk ‘{print $1}’` -f
   
   # 按条件筛选之后删除容器
   docker rm `docker ps -a | grep sca-routing-** | awk '{print $1}'`
   ```
