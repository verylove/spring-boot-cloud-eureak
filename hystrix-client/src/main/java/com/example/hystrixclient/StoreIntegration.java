package com.example.hystrixclient;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

@Component
public class StoreIntegration {

    @HystrixCommand(fallbackMethod = "defaultStores")
    public String getStores(String str) {
        //do stuff that might fail
        return "success"+str;
    }

    public String defaultStores(String str) {
        return "fail:"+str;
    }
}