package com.example.apigatewaydynamicfilter;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableConfigurationProperties({FilterConfiguration.class})
@SpringCloudApplication
public class ApiGatewayDynamicFilterApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ApiGatewayDynamicFilterApplication.class, args);
		new SpringApplicationBuilder(ApiGatewayDynamicFilterApplication.class).web(true).run(args);
	}

	@Bean
	public FilterLoader filterLoader(FilterConfiguration filterConfiguration){
		FilterLoader filterLoader = FilterLoader.getInstance();
		filterLoader.setCompiler(new GroovyCompiler());
		try {
			FilterFileManager.setFilenameFilter(new GroovyFileFilter());
			FilterFileManager.init(
					filterConfiguration.getInterval(),
					filterConfiguration.getRoot() + "/pre",
					filterConfiguration.getRoot() + "/post");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return filterLoader;
	}
}
