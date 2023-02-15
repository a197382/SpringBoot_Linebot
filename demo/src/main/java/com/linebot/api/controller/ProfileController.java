package com.linebot.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linebot.api.entity.Profile;
import com.linebot.api.respository.ProfileRepository;

@RestController
public class ProfileController {
    
    @Autowired
    private ProfileRepository profileResp;
    
    @RequestMapping("/listProfile")
    public List<Profile> listProfile() {
        return profileResp.findAll();
    }
}
