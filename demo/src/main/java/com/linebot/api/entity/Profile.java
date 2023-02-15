package com.linebot.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "profile")
public class Profile {
    @Id
    private String userId;
    private String displayName;
    private String language;
    private String pictureUrl;
    private String statusMessage;
}
