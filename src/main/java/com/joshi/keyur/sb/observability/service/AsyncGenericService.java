package com.joshi.keyur.sb.observability.service;

import com.joshi.keyur.sb.observability.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AsyncGenericService {
    private final WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(AsyncGenericService.class);
    AsyncGenericService(WebClient webClient){
        this.webClient = webClient;
    }
    public Mono<String> sayHello(){
        logger.info("Saying Hello");
        return webClient.get()
                .uri(AppConstants.FETCH_USER_PATH)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response->Mono.just("Hi, "+response));
    }

    public Mono<String> sayHelloWithDays(){
        logger.info("Saying Hello with days");
        return sayHello().flatMap(res->
            webClient.get()
                    .uri(AppConstants.FETCH_NUMBERS_PATH)
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .flatMap(response->{
                        return Mono.just(res+" it's been "+response+" days we met!");
                    }));
    }

    public Mono<String> sayComplexHelloWithDays(){
        logger.info("Saying Complex Hello");
        return webClient.get()
                .uri(AppConstants.SAY_HELLO_PATH)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(res->
                        webClient.get()
                                .uri(AppConstants.FETCH_NUMBERS_PATH)
                                .retrieve()
                                .bodyToMono(Integer.class)
                                .flatMap(response->{
                                    return Mono.just(res+" it's been "+response+" days we met!");
                                }));
    }
}
