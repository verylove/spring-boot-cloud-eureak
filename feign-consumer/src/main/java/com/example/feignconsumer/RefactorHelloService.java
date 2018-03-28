package com.example.feignconsumer;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "HELLO-SERVICE")
public interface RefactorHelloService extends com.example.helloserviceapi.service.HelloService {
}
