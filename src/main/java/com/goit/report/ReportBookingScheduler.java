package com.goit.report;

import com.goit.entity.Ticket;
import com.goit.entity.TicketStatus;
import com.goit.service.BookingTicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;


@Component
@Slf4j
@AllArgsConstructor
public class ReportBookingScheduler {
    private final BookingTicketService ticketService;
    private final TicketReportService reportService;


    @Transactional
    @Scheduled(cron = "${telegram.bot.scheduler.cron}")
    public void run() {
        log.info("Run the scheduler: " + LocalDateTime.now());
        final var tickets = ticketService.findTicketsByStatus(TicketStatus.BOOKED);
        if (!tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                ticketService.updateStatus(ticket.getTicketId(), TicketStatus.CONFIRMED);
                reportService.generateTicket(ticket);
            }
        }
    }
}
