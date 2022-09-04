package com.mailing.poc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    String API_KEY = "bc9fcbe0-af3a-49b8-a89d-e90209884e2c";
    
    String resourceUrl = "https://api.mcspoc.net/";

    Map<String, Integer> userIdMap = new HashMap<>();

    @PostConstruct
    public void setUserIdMap() {
        userIdMap.put("hw0001@m.m", 14);
    }

    @GetMapping(value = "/flowly/stress")
    public Map<String, Object> stress(@RequestParam("userId") String userId,
                       @RequestHeader(value="Authorization") String authorization) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);

        //인증정보 검증

        HttpEntity<String> request = new HttpEntity<>(headers);

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        ResponseEntity<String> response = restTemplate.exchange(
                resourceUrl + "/flowly/stress?userId="+userIdMap.get(userId),
                HttpMethod.GET,
                request,
                String.class
        );

        System.out.println("response = " + response);
        System.out.println("response status = " + response.getStatusCode());
        System.out.println("response = " + response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(response.getBody(), Map.class);

        System.out.println("map = " + map);

        return map;

    }
}
