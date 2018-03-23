# spring-boot-cloud-eureak
spring-boot-cloud    服务治理 包含spring boot cloud,eureak

微服务的实践，高效性能、负载均衡服务、服务治理、高可用接口、服务发现、注册中心

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