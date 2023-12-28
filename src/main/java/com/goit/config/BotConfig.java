package com.goit.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Getter
@Setter
public class BotConfig {

   // @Value("${telegram.bot.name}")
    private String name = "circus_nick_bot";
  //  @Value("${telegram.bot.token}")
    private String token = "6755867756:AAHviBieLQKyGOTYlm5paCcCk3vktaOExFU";

}
