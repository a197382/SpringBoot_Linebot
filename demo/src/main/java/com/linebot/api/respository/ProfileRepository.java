package com.linebot.api.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.linebot.api.entity.Profile;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

}
