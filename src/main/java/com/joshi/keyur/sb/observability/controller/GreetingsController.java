package com.joshi.keyur.sb.observability.controller;

import com.joshi.keyur.sb.observability.service.AsyncGenericService;
import com.joshi.keyur.sb.observability.service.SyncGenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class GreetingsController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingsController.class);
    private final AsyncGenericService asyncGenericService;
    private final SyncGenericService syncGenericService;
    public GreetingsController(AsyncGenericService asyncGenericService, SyncGenericService syncGenericService){
        this.asyncGenericService = asyncGenericService;
        this.syncGenericService = syncGenericService;
    }
    @GetMapping("/sayHi")
    public String sayHi(){
        return syncGenericService.sayHi();
    }
    @GetMapping("/sayHello")
    public Mono<String> sayHello(){
        logger.info("Will Say Hello");
        return asyncGenericService.sayHello();
    }
    @GetMapping("/sayHelloWithDays")
    public Mono<String> sayHelloWithDays(){
        logger.info("Will Say Hello with days");
        return asyncGenericService.sayHelloWithDays();
    }
}
