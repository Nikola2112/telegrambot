package com.goit.command.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;


@AllArgsConstructor
@Data
public class CustomerInput {
        private static final String EMPTY = null;
private Long chatId;
private String firstName;
private String lastName;
private Timestamp registeredAt;
private String command;
private String value;

public CustomerInput(Long chatId, String firstName, String lastName, Timestamp registeredAt, String command) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registeredAt = registeredAt;
final var split = command.split("-");
        if (split.length == 1) {
        this.command = split[0];
        this.value = EMPTY;
        } else {
        this.command = split[0].trim();
        this.value = split[1].trim();
        }
        }
        }