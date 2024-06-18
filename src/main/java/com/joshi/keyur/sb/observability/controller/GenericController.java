package com.joshi.keyur.sb.observability.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class GenericController {
    RestTemplate restTemplate;
    GenericController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @GetMapping("/sayHi")
    public String sayHi(){
        String user = restTemplate
                .getForEntity("http://localhost:8080/api/fetchUser",String.class)
                .getBody();
        return "Hi, "+user;
    }
    @GetMapping("/fetchUser")
    public String fetchUser(){
        return "Spiderman";
    }
}
