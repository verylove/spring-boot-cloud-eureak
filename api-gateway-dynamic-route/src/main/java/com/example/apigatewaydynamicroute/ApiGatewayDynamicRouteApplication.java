package com.example.apigatewaydynamicroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

@EnableZuulProxy
@SpringCloudApplication
public class ApiGatewayDynamicRouteApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ApiGatewayDynamicRouteApplication.class, args);
		new SpringApplicationBuilder(ApiGatewayDynamicRouteApplication.class).web(true).run(args);
	}


	//將zuul的配置内容动态化
	@RefreshScope
	@ConfigurationProperties("zuul")
	public ZuulProperties zuulProperties(){
		return new ZuulProperties();
	}

}
