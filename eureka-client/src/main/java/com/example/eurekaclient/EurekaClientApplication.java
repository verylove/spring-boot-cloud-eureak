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
import java.util.Random;

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

		//让处理线程等待几秒钟，模拟服务阻塞状态，Hystrix默认超时时间为2000毫秒，这里采用0~3000随机
		int sleepTime = new Random().nextInt(3);
		LOGGER.info("sleepTime:"+ sleepTime);

		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("provider service, host = " + instance.getHost()
				+ ", service_id = " + instance.getServiceId());

		return "Hello,Provider!";
	}


	public String helloFallback(){
		return "error";
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
