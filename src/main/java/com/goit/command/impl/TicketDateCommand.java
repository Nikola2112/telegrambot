package com.goit.command.impl;


import com.goit.command.constant.Answer;
import com.goit.command.input.CustomerInput;
import com.goit.dto.TicketDto;

import com.goit.service.TicketService;
import com.goit.validator.DateValidator;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TicketDateCommand extends MainCommand {
    public static final String COMMAND_NAME = "/date_of_visit";
    private final TicketService ticketService;

    public TicketDateCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public SendMessage handle(CustomerInput customerInput) {
        if (!isValidDate(customerInput)) {
            String answer = String.format(Answer.MESSAGE_DATE_INVALID, customerInput.getValue());
            return new SendMessage(String.valueOf(customerInput.getChatId()), answer);
        }

        LocalDateTime parseDateTime = parseLocalDateTime(customerInput);

        if (isPastDate(parseDateTime)) {
            return new SendMessage(String.valueOf(customerInput.getChatId()), Answer.MESSAGE_PAST_DATE);
        }

        ticketService.createTicket(new TicketDto(customerInput.getChatId(), parseDateTime));
        return new SendMessage(String.valueOf(customerInput.getChatId()), Answer.MESSAGE_DATE_VALID);
    }

    private boolean isValidDate(CustomerInput customerInput) {
        return customerInput.getValue() != null && new DateValidator().isValid(String.valueOf(customerInput.getValue()));
    }

    private LocalDateTime parseLocalDateTime(CustomerInput customerInput) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String input = customerInput.getValue().orElseThrow(() -> new IllegalArgumentException("Input value is empty"));
        return LocalDateTime.parse(input, dateTimeFormat);
    }
    private boolean isPastDate(LocalDateTime dateTime) {
        return LocalDateTime.now().isAfter(dateTime);
    }
}