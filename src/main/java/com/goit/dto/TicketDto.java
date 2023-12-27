package com.goit.dto;


import com.goit.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TicketDto {

    private Long chatId;
    private LocalDateTime dateOfVisit;

    public Ticket toTicket() {
        return Ticket.builder()
                .chatId(chatId)
                .dateOfVisit(dateOfVisit)
                .build();
    }
}
