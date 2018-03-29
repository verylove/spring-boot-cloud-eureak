package com.example.feignconsumer;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients//打开Feign注解
@EnableDiscoveryClient
@SpringBootApplication
public class FeignConsumerApplication {

	//也可以创建FullLogConfiguration,在@FeignClient(name="HELLO-SERVICE",configuration=FullLogConfiguration.class)
	/*
	   @Configuration
	   public class FullLogConfiguration{
			@Bean
			Logger.Level feignLoggerLevel(){
				return Logger.Level.FULL;
			}
	   }
	 */
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignConsumerApplication.class, args);
	}
}
