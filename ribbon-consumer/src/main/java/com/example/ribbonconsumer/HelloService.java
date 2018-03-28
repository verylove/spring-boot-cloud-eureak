package com.example.ribbonconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@EnableCircuitBreaker
public class HelloService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }

    //尝试回退处理，也成为”服务降级“
    public String helloFallback(){
        return "error ribbon consumer";
    }


    //@HystrixCommand(fallbackMethod = "helloFallback",commandKey = "helloKey",commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
    @HystrixCommand(fallbackMethod = "helloFallback",commandKey = "helloKey")
    public  String hello(){

        String result = "haha";
        long start = System.currentTimeMillis();

        result = restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();

        long end = System.currentTimeMillis();
        LOGGER.info("Spend time:"+(end -start));

        return result.toString();
    }



}
