package com.joshi.keyur.sb.observability.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class GenericController {

    @Value("${superhero}")
    private String superHero;
    private final RestTemplate restTemplate;
    private final WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(GenericController.class);
    public GenericController(RestTemplate restTemplate,WebClient webClient){
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }
    @GetMapping("/sayHi")
    public String sayHi(){
        String portNumber = "8095";
        double num = Math.random();
        logger.info("Will say Hi {}",num);
        if(num<0.5){
            portNumber = "8096";
        }
        String name = restTemplate
                .getForObject("http://localhost:"+portNumber+"/api/fetchUser",String.class);
        return "Hi, "+name;
    }
    @GetMapping("/fetchUser")
    public String fetchUser(){
        logger.info("Returning {}!",superHero);
        return superHero;
    }
    @GetMapping("/sayHello")
    public Mono<String> sayHello(){
        logger.info("Will Say Hello");
        return webClient.get()
                .uri("/api/fetchUser")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response->Mono.just("Hi, "+response));
    }
}
