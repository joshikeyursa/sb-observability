package com.joshi.keyur.sb.observability.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    RestTemplate buildRestTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
    @Bean
    WebClient buildWebClient(WebClient.Builder webClientBuilder,@Value("${downstream.basepath}")String downstreamBasepath){
        return webClientBuilder
                .baseUrl(downstreamBasepath)
                .build();
    }
}
