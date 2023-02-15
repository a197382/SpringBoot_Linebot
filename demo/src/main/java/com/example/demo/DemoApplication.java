package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.linebot.api.respository.ProfileRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages={"com.linebot"})
@EnableMongoRepositories(basePackageClasses = ProfileRepository.class)
public class DemoApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
