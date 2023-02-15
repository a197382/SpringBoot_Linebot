package com.linebot.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.linebot.api.entity.Profile;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProfileService {
    
    private final RestTemplate restTemplate;
    
    @Value("${line.bot.channelToken}")
    String accessToken;
    @Value("${line.bot.profile.url}")
    String profileApiUrl;
    
    public ProfileService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
    public ResponseEntity<Profile> getUserProfile(String userId) {
        ResponseEntity<Profile> result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            result = this.restTemplate.exchange(profileApiUrl + userId, HttpMethod.GET, requestEntity, Profile.class);
        } catch(Exception e) {
            log.error(e.getMessage(), e);;
        }
        return result;
    }
    
}
