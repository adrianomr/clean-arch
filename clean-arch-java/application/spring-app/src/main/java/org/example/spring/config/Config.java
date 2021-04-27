package org.example.spring.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.SpringConfig;
import org.example.controller.UserController;
import org.example.usecase.CreateUser;
import org.example.usecase.FindUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private final SpringConfig config = new SpringConfig();

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    @Bean
    public CreateUser createUser() {
        return config.createUser();
    }

    @Bean
    public FindUser findUser() {
        return config.findUser();
    }

    @Bean
    public UserController userController() {
        return new UserController(createUser(), findUser());
    }
}

