package com.goit.command.impl;


import com.goit.command.constant.Answer;
import com.goit.command.input.CustomerInput;
import com.goit.entity.Ticket;
import com.goit.excetpion.ResourcesNotFound;
import com.goit.service.BookingTicketService;
import com.goit.validator.DateValidator;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class TicketDateCommand extends MainCommand {
    public static final String COMMAND_NAME = "/date_of_visit";
    private final BookingTicketService ticketService;

    public TicketDateCommand(BookingTicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public boolean supports(String name) {
        return super.supports(name);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public SendMessage handle(CustomerInput customerInput) {
         var validator = new DateValidator();
        if (customerInput.getValue() == null || !validator.isValid(customerInput.getValue())) {
            final var answer = String.format(Answer.MESSAGE_DATE_INVALID, customerInput.getValue());
            return new SendMessage(String.valueOf(customerInput.getChatId()), answer);
        }
        final var ticket = new Ticket(customerInput.getChatId(), customerInput.getValue());
        if (ticket.isAfter()) {
            return new SendMessage(String.valueOf(customerInput.getChatId()), Answer.MESSAGE_PAST_DATE);
        }
        try {
            ticketService.createTicket(ticket);
            return new SendMessage(String.valueOf(customerInput.getChatId()), Answer.MESSAGE_DATE_VALID);
        } catch (ResourcesNotFound e) {
            return new SendMessage(String.valueOf(customerInput.getChatId()), Answer.MESSAGE_USER_IS_NOT_REGISTER);
        }
    }
}