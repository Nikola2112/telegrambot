package com.goit.entity;


import com.goit.utils.DateTimeFormatters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;
    @Column(name = "customer_id")
    private Long customerId;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(unique = true)
    private LocalDateTime dateOfVisit;

    public Ticket(Long customerId, String dateOfVisit) {
        this.customerId = customerId;
        this.dateOfVisit = DateTimeFormatters.localDateTimeStringParser(dateOfVisit);
        this.status = TicketStatus.BOOKED;
    }

    public boolean isAfter() {
        final var dateTimeNow = LocalDateTime.now();
        return dateTimeNow.isAfter(dateOfVisit);
    }

    public void updateStatus(TicketStatus ticketStatus) {
        this.status = ticketStatus;
    }
}
