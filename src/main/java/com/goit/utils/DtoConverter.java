package com.goit.utils;


import com.goit.command.input.CustomerInput;
import com.goit.dto.RegisteredCustomerDto;
import com.goit.entity.CustomerRole;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Timestamp;

public class DtoConverter {

    public static RegisteredCustomerDto toRegisteredCustomerDto(Update update) {
        return RegisteredCustomerDto.builder()
                .chatId(update.getMessage().getChatId())
                .firstName(update.getMessage().getContact().getFirstName())
                .lastName(update.getMessage().getContact().getLastName())
                .phoneNumber(update.getMessage().getContact().getPhoneNumber())
                .role(CustomerRole.ROLE_USER)
                .registeredAt(new Timestamp((long) update.getMessage().getDate() * 1000))
                .build();
    }

    public static CustomerInput toCustomerInput(final Update update) {
        return new CustomerInput(
                update.getMessage().getChatId(),
                update.getMessage().getChat().getFirstName(),
                update.getMessage().getChat().getLastName(),
                new Timestamp(update.getMessage().getDate()),
                update.getMessage().getText()
        );
    }
}
