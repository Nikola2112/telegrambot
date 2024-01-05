package com.goit.service;

import com.goit.entity.*;
import com.goit.excetpion.ResourcesNotFound;
import com.goit.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class BookingTicketService {
    private final CustomerService customerService;
    private final TicketRepository ticketRepository;

    public Ticket findTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourcesNotFound(ticketId));
    }

    @Transactional
    public void createTicket(Ticket ticket) {
        var customer = customerService.findById(ticket.getCustomerId());
        customer.addTicker(ticket);
        log.info(customer.getFullName() + " booked a ticket!");
    }

    @Transactional
    public void updateStatus(Long ticketId, TicketStatus ticketStatus) {
        Ticket ticket = findTicketById(ticketId);
        ticket.updateStatus(ticketStatus);
    }

    public List<Ticket> findTicketsByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status);
    }

}
