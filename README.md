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
