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
		//None，不记录任何信息
		//BASIC,仅记录请求方法、URL及相应码和执行时间
		//HEADERS,除了BASIC级别的信息外，还会记录相应信息头和时间
		//FULL,记录全部
		return Logger.Level.FULL;
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignConsumerApplication.class, args);
	}
}
