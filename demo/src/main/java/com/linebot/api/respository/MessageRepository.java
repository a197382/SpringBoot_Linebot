package com.linebot.api.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.linebot.api.entity.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

}
