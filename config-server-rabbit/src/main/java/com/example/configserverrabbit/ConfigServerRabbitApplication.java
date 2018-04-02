package com.example.configserverrabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConfigServerRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerRabbitApplication.class, args);
	}
}
