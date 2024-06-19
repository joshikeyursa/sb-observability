package com.joshi.keyur.sb.observability.service;

import com.joshi.keyur.sb.observability.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SyncGenericService {
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SyncGenericService.class);
    public SyncGenericService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public String sayHi(){
        String portNumber = "8095";
        double num = Math.random();
        logger.info("Will say Hi {}",num);
        if(num<0.5){
            portNumber = "8096";
        }
        String name = restTemplate
                .getForObject("http://localhost:"+portNumber+ AppConstants.FETCH_USER_PATH,String.class);
        return "Hi, "+name;
    }
}
