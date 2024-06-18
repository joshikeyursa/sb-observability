package com.joshi.keyur.sb.observability.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class GenericController {

    @Value("${superhero}")
    private String superHero;
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(GenericController.class);
    public GenericController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(){
        logger.info("Will say Hi");
        String portNumber = "8095";
        if(Math.random()<0.5){
            portNumber = "8096";
        }
        String name = restTemplate
                .getForObject("http://localhost:"+portNumber+"/api/fetchUser",String.class);
        return ResponseEntity.ok("Hi, "+name);
    }
    @GetMapping("/fetchUser")
    public ResponseEntity<String> fetchUser(){
        logger.info("Returning {}!",superHero);
        return ResponseEntity.ok(superHero);
    }
}
