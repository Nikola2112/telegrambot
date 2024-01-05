package com.goit.operator;

import com.goit.dto.RegisteredCustomerDto;
import com.goit.entity.CustomerRole;
import com.goit.service.CustomerService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;
import java.time.LocalDateTime;


@Component
@Slf4j
@RequiredArgsConstructor
public class OperatorInitializer {

    private final OperatorProperties properties;
    private final CustomerService customerService;


    @PostConstruct
    public void createOperator() {
        boolean operatorExists = customerService.existsByChatId(properties.getChatId());
        if (!operatorExists) {
            customerService.registered(
                    RegisteredCustomerDto.builder()
                            .chatId(properties.getChatId())
                            .firstName(properties.getFirstName())
                            .lastName(properties.getLastName())
                            .phoneNumber(properties.getPhoneNumber())
                            .role(CustomerRole.ROLE_OPERATOR)
                            .registeredAt(Timestamp.valueOf(LocalDateTime.now()))
                            .build());
            log.info("Registered operator with ID: " + properties.getChatId());
        }
    }
}
