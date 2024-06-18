package com.joshi.keyur.sb.observability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SbObservabilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbObservabilityApplication.class, args);
	}
	@Bean
	public RestTemplate buildRestTemplate(RestTemplateBuilder builder){
		return builder.build();
	}
}
