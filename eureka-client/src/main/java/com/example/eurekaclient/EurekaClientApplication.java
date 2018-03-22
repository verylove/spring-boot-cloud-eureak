package com.example.eurekaclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableDiscoveryClient
@EnableEurekaClient
@RestController
@SpringBootApplication
@EnableAutoConfiguration
public class EurekaClientApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(EurekaClientApplication.class);

	@Autowired
	private Registration registration;       // 服务注册

	@Autowired
	private DiscoveryClient client; // 服务发现客户端

	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String index() {
		ServiceInstance instance = serviceInstance();
		LOGGER.info("provider service, host = " + instance.getHost()
				+ ", service_id = " + instance.getServiceId());
		return "Hello,Provider!";
	}

	/**
	 * 获取当前服务的服务实例
	 *
	 * @return ServiceInstance
	 */
	public ServiceInstance serviceInstance() {
		List<ServiceInstance> list = client.getInstances(registration.getServiceId());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
}
