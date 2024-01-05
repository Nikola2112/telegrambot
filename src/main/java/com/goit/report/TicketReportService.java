package com.goit.report;

import com.goit.service.TelegramBotStarter;
import com.goit.command.constant.Answer;
import com.goit.entity.Customer;
import com.goit.entity.Ticket;
import com.goit.excetpion.TicketGeneratorException;
import com.goit.operator.NotificationOperator;
import com.goit.service.CustomerService;
import com.goit.utils.DateTimeFormatters;
import com.goit.utils.RenameFiles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;


@Component
@RequiredArgsConstructor
public class TicketReportService {

    private static final String DIRECTORY = "./tickets/";
    private final CustomerService customerService;
    private final TelegramBotStarter telegramBotStarter;
    private final NotificationOperator operatorService;


    public void generateTicket( Ticket ticket) {
         var customer = customerService.findById(ticket.getCustomerId());
         var fileName = RenameFiles.renameFile(customer.getFullName(), ticket.getDateOfVisit());
         var path = Paths.get(DIRECTORY + fileName);
         var text = textFormat(customer, ticket);
        try {
            Files.writeString(path, text, UTF_8);
            sendDocument(path.toString(), ticket);
            operatorService.notifyOperator(ticket);
        } catch (IOException | TelegramApiException e) {
            throw new TicketGeneratorException("An error occurred while generating the ticket: " + e.getMessage());
        }
    }

    private void sendDocument(String fileName, Ticket ticket) throws TelegramApiException {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(ticket.getCustomerId());
        sendDocument.setDocument(new InputFile(new File(fileName)));
        telegramBotStarter.execute(sendDocument);
    }

    private String textFormat(Customer customer, Ticket ticket) {
         var sb = new StringBuilder();
        sb.append("Welcome to Circus \"Orbit\"")
                .append(Answer.BLUSH)
                .append("%n")
                .append("Name: %s%n")
                .append("Status: %s%n")
                .append("Phone number: %s%n")
                .append("Date fo visit: %s%n")
                .append("Thank you for booking a ticket!");
        return String.format(sb.toString(),
                customer.getFullName(),
                ticket.getStatus(),
                customer.getPhoneNumber(),
                DateTimeFormatters.localDateTimeParser(ticket.getDateOfVisit())
        );
    }
}
