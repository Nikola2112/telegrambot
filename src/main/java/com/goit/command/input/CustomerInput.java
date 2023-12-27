package com.goit.command.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Timestamp;
import java.util.Optional;

@AllArgsConstructor
@Data
public class CustomerInput {

    private static final String EMPTY = null;
    private Long chatId;
    private String firstName;
    private String lastName;
    private Timestamp registeredAt;
    private String command;
    private Optional<String> value;

    public CustomerInput(Long chatId, String firstName, String lastName, java.sql.Timestamp timestamp, String text) {
    }


    public static CustomerInput createFromCommand(Long chatId, String firstName, String lastName, Timestamp registeredAt, String command) {
        final var split = command.split("-");
        if (split.length == 1) {
            return new CustomerInput(chatId, firstName, lastName, registeredAt, split[0], Optional.empty());
        } else {
            return new CustomerInput(chatId, firstName, lastName, registeredAt, split[0].trim(), Optional.of(split[1].trim()));
        }
    }
}