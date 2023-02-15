package com.linebot.api.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "messagesLog")
public class Message {
    private String userId;
    private String text;
    private LocalDateTime timestamp;
}
