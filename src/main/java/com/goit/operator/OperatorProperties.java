package com.goit.operator;

import lombok.Data;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.sql.Timestamp;

@Value
@ConfigurationProperties("telegram.bot.operator")
@Data
public class OperatorProperties {

    Long chatId;
    String firstName;
    String lastName;
    String phoneNumber;
    Timestamp registeredAt;

}
