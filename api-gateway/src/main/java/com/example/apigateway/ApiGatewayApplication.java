package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ApiGatewayApplication.class, args);
		new SpringApplicationBuilder(ApiGatewayApplication.class).web(true).run(args);
	}

	@Bean
	public AccessFilter accessFilter(){
		return new AccessFilter();
	}
}
