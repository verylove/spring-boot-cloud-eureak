# spring-boot-cloud-eureak
spring-boot-cloud    服务治理 包含spring boot cloud,eureak

微服务的实践，高效性能、负载均衡服务、服务治理、高可用接口、服务发现、注册中心、API网关、配置中心

# 第一阶段
## 启动注册中心
```
java -jar eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1
java -jar eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2
```

## 启动客户端服务器提供
```
java -jar eureka-client-0.0.1-SNAPSHOT.jar --server.port=8081
java -jar eureka-client-0.0.1-SNAPSHOT.jar --server.port=8082
```

## 启动消费者
```
java -jar ribbon-consumer-0.0.1-SNAPSHOT.jar
```

visit http://localhost:9000/ribbon-consumer


# 第二阶段

## 增加熔断、短路机制、监控平台

能引擎服务器降级的集中情况：
1. 当前命令处理“熔断、短路”状态，短路器是打开的时候
2. 当前命令的线程池、请求队列或者信息号量被占满的时候
3. HystrixObservableCommand.construct()或HystrixCommand.run()抛出异常的时候  
   > 具体参考：https://github.com/Netflix/Hystrix/wiki/Configuration

### 启动监控平台hystrix-dashboard 

   > 访问 http://localhost:2001/hystrix

4. 增加Hystrix 服务器容错、断路器，Hystrix客户端未能实现hystrix.stream 端点监控，先用旧版test-hystrix-client代替
（Spring Cloud Hystrix 实现了断路器、线路隔离等一系列服务保护功能。
它也是基于 Netflix 的开源框架 Hystrix 实现的，该框架的目标在于通过控制那些访问远程系统、服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。
Hystrix 具备服务降级、服务熔断、线程和信号隔离、请求缓存、请求合并以及服务监控等强大功能。）  

5. 简单的使用 spring cloud feign 声明式调用hello-service服务   

6. 使用localhost:9001/feign-consumer3 访问hello-service，简化代码，使用hello-service-api作为公共接口库，实现其类和接口，实现类为RefactorHelloService  


7. 增加fallback ，不开启hello-server，自动服务降级  

     小结  
        eureak [jʊ'rikə]，实现高可用的服务注册中心及微服务的注册与发现  
        ribbon  ['rɪbən]、Feign  [fen]，实现服务间负载均衡的接口调用   
        hystrix ，实现线程隔离并加入熔断机制   
        一般ribbon超时要小于hystrix

9. 增加zuul 对api访问接口转发路由，在增加accessfilter增加api接口访问权限控制     

   * 使用  
        * http://localhost:5555/api-a/hello 访问HELLO-SERVICE的hello方法  
        * http://localhost:5555/api-b/feign-consumer?accessToken=token 访问FEIGN-CONSUMER的feign-consumer方法 
         
   1. 默认zuul增加eureak时会自动注册路由，可以适使用localhost:5555/<service-name>/<function-name>  
   2. 不加accessToken 会报401错误  
   3. 简单配置写法可以是zuul.routes.<serviceId>=<path>  
   
 10. 增加api-gateway-dynamic-filter 使用groovy配置过滤器   
       
       在pre/post动态定义增加过滤器，增强API网关服务能力，修改配置后，不用重启服务  
       这里已经增加上配置中心了Spring Cloud Config ,使用的Git仓库
       
 11. 使用http://localhost:7001/didispace/prod/config-label-test访问，可以读取didispace应用的prod配置信息 
 
 12. 增加config-client 使用 http://localhost:7002/from 访问，从配置中心7001读取git仓库远程配置信息
      增加访问权限，通过密码访问接口
 
 13. 实践spring cloud bus 使用config-server-rabbit 实现增加/bus/refresh 
 
 14. 增加stream-hello 测试使用消息驱动 *
 
 15. 集成分布式服务器跟踪Sleuth，增加Logstash日志输出，trace-1 没有实现，trace-2会报错
 
 16. 通过ELK 收集信息, 与Zipkin整合，搭建Zipkin Server,使用localhost:9411访问Trace信息
 
       1). ElasticSearch  
       2). Logstash  
       3). Kibana  
 
 
 
 
         
         
      附录：
        Spring Boot 加载顺序  
        1. 在命令行传入的参数  
        2. SPRING APPLICATION JSON中的属性。 SPRING_APPLICATION—JSON是以 JSON格式配置在系统环境变量中的内容。   
        3. java:comp/env中的JNDI 属性。   
        4. Java的系统属性， 可以通过System.getProperties()获得的内容。   
        5. 操作系统的环境变量。   
        6. 通过random.*配置的随机属性。  
        7. 位于当前应用jar 包之外，针对不同{profile}环境的配置文件内容，例如 application-{profile}.properties或是YAML定义的配置文件。   
        8. 位于当前应用jar 包之内，针对不同{profile}环境的配置文件内容，例如 application-{profile}.properties或是YAML定义的配置文件。   
        9. 位于当前应用jar包之外的application.properties和YAML配置内容。   
        10. 位于当前应用jar包之内的app口cation.properties和YAML配置内容。   
        11. 在@Configuration注解修改的类中，通过@PropertySource注解定义的属性。   
        12. 应用默认属性，使用SpringApplication.setDefaultProperties 定义的内容。   
     
      JCE 版本 (Unlimited Strength Java Cryptography Extension）
       http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
 
 