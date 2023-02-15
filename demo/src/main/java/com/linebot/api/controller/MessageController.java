package com.linebot.api.controller;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.linebot.api.entity.Message;
import com.linebot.api.entity.Profile;
import com.linebot.api.respository.MessageRepository;
import com.linebot.api.respository.ProfileRepository;
import com.linebot.api.service.ProfileService;
import com.linebot.api.service.PushService;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.extern.slf4j.Slf4j;

@LineMessageHandler
@RestController
@Slf4j
public class MessageController {
    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private ProfileRepository profileResp;
    
    @Autowired
    private PushService pushService;
    
    @Autowired
    private MessageRepository msgResp;
    
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("Receive Line Msg: " + event);
        
        Profile userProfile = profileService.getUserProfile(event.getSource().getUserId()).getBody();
        Optional<Profile> profile = profileResp.findById(userProfile.getUserId());
        
        if (!profile.isPresent()) {
            profileResp.save(userProfile);
        }
        
        msgResp.save(Message.builder().userId(userProfile.getUserId()).text(event.getMessage().getText()).timestamp(LocalDateTime.ofInstant(event.getTimestamp(), ZoneOffset.UTC)).build());
        
        return new TextMessage(event.getMessage().getText());
    }
    
    @RequestMapping("/pushMessage")
    public HttpStatusCode pushMessage(@RequestParam String userId,@RequestParam String text) {
        HttpStatusCode result = pushService.pushMessage(userId, text);
        return result;
    }
}
