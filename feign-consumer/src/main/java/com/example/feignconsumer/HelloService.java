package com.example.feignconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "hello-service")
public interface HelloService {
    @RequestMapping("/hello")
    String hello();
}
