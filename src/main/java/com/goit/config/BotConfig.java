package com.goit.config;

import org.springframework.context.annotation.Configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
@Data
public class BotConfig {

    @Value("${telegram.bot.name}")
    private String name;
    @Value("${telegram.bot.token}")
    private String token;

}
