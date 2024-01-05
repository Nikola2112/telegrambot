package com.goit.operator;

import com.goit.service.TelegramBotStarter;
import com.goit.entity.Customer;
import com.goit.entity.CustomerRole;
import com.goit.entity.Ticket;
import com.goit.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;



@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationOperator {

    private final TelegramBotStarter telegramBotStarter;
    private final CustomerService customerService;

    public void notifyOperator(Ticket ticket) {
         var operators = customerService.findByRole(CustomerRole.ROLE_OPERATOR);
         var customer = customerService.findById(ticket.getCustomerId());
        if (!operators.isEmpty()) {
            for (Customer operator : operators) {
                final var message = new SendMessage();
                message.setChatId(operator.getChatId());
                message.setText(textFormat(customer));
                telegramBotStarter.sendMessage(message);
                log.info("A notification was sent to the operator with ID: " + operator.getChatId());
            }
        }
    }

    private String textFormat(Customer operator) {
        final var text = "User: %s booked a ticket!";
        return String.format(text, operator.getFullName());
    }
}
