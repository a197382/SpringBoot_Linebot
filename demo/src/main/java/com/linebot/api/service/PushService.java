package com.linebot.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PushService {
    
    private final RestTemplate restTemplate;
    
    @Value("${line.bot.channelToken}")
    String accessToken;
    @Value("${line.bot.push.url}")
    String pushApiUrl;

    
    public PushService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
    public HttpStatusCode pushMessage(String userId, String text) {
        HttpStatusCode resultCode = HttpStatus.BAD_REQUEST;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            headers.setContentType(MediaType.APPLICATION_JSON);
            PushMessage pushMsg = new PushMessage(userId, TextMessage.builder().text(text).build());
            HttpEntity<PushMessage> requestEntity = new HttpEntity<PushMessage>(pushMsg, headers);
            ResponseEntity<String> result = restTemplate.exchange(pushApiUrl, HttpMethod.POST, requestEntity, String.class);
            log.info(String.format("Push Msg: %s, Result: %s", text, result.toString()));
            resultCode = result.getStatusCode();
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        return resultCode;
    }
    
}
