# spring-boot-cloud-eureak
spring-boot-cloud    服务治理 包含spring boot cloud,eureak

微服务的实践，高效性能、负载均衡服务、服务治理、高可用接口、服务发现、注册中心

#第一阶段
##启动注册中心
```
java -jar eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1
java -jar eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2
```

##启动客户端服务器提供
```
java -jar eureka-client-0.0.1-SNAPSHOT.jar --server.port=8081
java -jar eureka-client-0.0.1-SNAPSHOT.jar --server.port=8082
```

##启动消费者
```
java -jar ribbon-consumer-0.0.1-SNAPSHOT.jar
```

visit http://localhost:9000/ribbon-consumer


#第二阶段

##增加熔断、短路机制

能引擎服务器降级的集中情况：
1、当前命令处理“熔断、短路”状态，短路器是打开的时候
2、当前命令的线程池、请求队列或者信息号量被占满的时候
3、HystrixObservableCommand.construct()或HystrixCommand.run()抛出异常的时候
具体参考：https://github.com/Netflix/Hystrix/wiki/Configuration

### 启动监控平台hystrix-dashboard 

访问 http://localhost:2001/hystrix

4、增加Hystrix 服务器容错、断路器，Hystrix客户端未能实现hystrix.stream 端点监控，先用旧版test-hystrix-client代替
（Spring Cloud Hystrix 实现了断路器、线路隔离等一系列服务保护功能。
它也是基于 Netflix 的开源框架 Hystrix 实现的，该框架的目标在于通过控制那些访问远程系统、服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。
Hystrix 具备服务降级、服务熔断、线程和信号隔离、请求缓存、请求合并以及服务监控等强大功能。）

5、简单的使用 spring cloud feign 调用hello-service服务 

6、使用localhost:9001/feign-consumer3 访问hello-service，简化代码，使用hello-service-api作为公共接口库，实现其类和接口，实现类为RefactorHelloService

