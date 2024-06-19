package com.joshi.keyur.sb.observability.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/data")
public class DataController {
    @Value("${superhero}")
    private String superHero;

    private final Random random=new Random();
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
    @GetMapping("/fetchUser")
    public String fetchUser(){
        logger.info("Returning {}!",superHero);
        return superHero;
    }
    @GetMapping("/fetchNumber")
    public Integer fetchNumber(){
        Integer randomNumber = random.nextInt(100);
        logger.info("Returning {}",randomNumber);
        return randomNumber;
    }
}
