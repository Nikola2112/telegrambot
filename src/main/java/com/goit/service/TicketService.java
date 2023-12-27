package com.goit.service;

import com.goit.dto.TicketDto;
import com.goit.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final CustomerService customerService;

    @Transactional
    public void createTicket(TicketDto ticketDto) {
        Customer customer = customerService.findById(ticketDto.getChatId());
        customer.addTicker(ticketDto.toTicket());
    }
}
