package com.mailing.poc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class Controller {

    String API_KEY = "bc9fcbe0-af3a-49b8-a89d-e90209884e2c";

    String resourceUrl = "https://api.mcspoc.net/";

    Map<String, Integer> userIdMap = new HashMap<>();

    @PostConstruct
    public void setUserIdMap() {
        userIdMap.put("hw0001@m.m", 14);

        for(int i=2; i<=20; i++) {
            userIdMap.put("julien"+i, 14);
        }
    }

    @GetMapping(value = "/flowly/stress")
    public Map<String, Object> stress(@RequestParam("userId") String userId,
                       @RequestHeader(value="Authorization") String authorization) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);

        //인증정보 검증
        Integer mcsUserId = userIdMap.get(userId);
        log.info("userId={}", userId);
        //log.info("userId={}", userId.substring(0, userId.charAt(' ')) + '+' + userId.substring(userId.charAt(' ')+1));
        log.info("mcsUserId={}", mcsUserId);
        if(mcsUserId == null) {
            new ObjectMapper();
        }

        HttpEntity<String> request = new HttpEntity<>(headers);

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        ResponseEntity<String> response = restTemplate.exchange(
                resourceUrl + "/flowly/stress?userId="+mcsUserId,
                HttpMethod.GET,
                request,
                String.class
        );

        System.out.println("response = " + response);
        System.out.println("response status = " + response.getStatusCode());
        System.out.println("response = " + response.getBody());


        Map<String, Object> map = mapper.readValue(response.getBody(), Map.class);

        System.out.println("map = " + map);

        return map;

    }

    @PostMapping(value = "/flowly/content")
    public Map<String, Object> content(@RequestBody RequestDto body,
                                       @RequestHeader(value="Authorization") String authorization) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);

        int userId = userIdMap.get(body.getUserId());
        DDRequestDto ddRequestDto = new DDRequestDto(userId, body.getMetadata());
        System.out.println("request : " + ddRequestDto.toString());

        HttpEntity<DDRequestDto> request = new HttpEntity<>(ddRequestDto, headers);

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        ResponseEntity<String> response = restTemplate.exchange(
                resourceUrl + "/flowly/content",
                HttpMethod.POST,
                request,
                String.class
        );

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(response.getBody(), Map.class);

        System.out.println("response = " + response);
        System.out.println("response status = " + response.getStatusCode());
        System.out.println("response = " + response.getBody());

        return map;
    }

    @PostMapping(value = "/flowly/test")
    public Map<String, Object> test(@RequestHeader(value="Authorization") String authorization) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);

        HttpEntity<DDRequestDto> request = new HttpEntity<>(headers);

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        ResponseEntity<String> response = restTemplate.exchange(
                resourceUrl + "/flowly/test",
                HttpMethod.POST,
                request,
                String.class
        );

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(response.getBody(), Map.class);

        System.out.println("response = " + response);
        System.out.println("response status = " + response.getStatusCode());
        System.out.println("response = " + response.getBody());

        return map;
    }

}
