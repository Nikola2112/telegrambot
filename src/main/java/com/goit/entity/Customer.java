package com.goit.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long chatId;

    @NotBlank(message = "Ім'я не повинно бути порожнім")
    @Column(name = "customer_firstName", length = 50)
    private String firstName;

    @NotBlank(message = "Прізвище не повинно бути порожнім")
    @Column(name = "customer_lastname", length = 50)
    private String lastName;

    @NotBlank(message = "Дата відвідування не повинна бути порожньою")
    @Column(name = "ticket_date")
    private LocalDateTime ticket_date;

    @Pattern(regexp = "\\+380\\d{9}", message = "Номер телефону повинен бути в форматі +380XXXXXXXXX")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "register_at")
    private Timestamp registeredAt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    public void addTicker(Ticket ticket) {
        if (ticket != null) {
            tickets.add(ticket);
        }
    }

    public void updatePhoneNumber(String phoneNumber) {
        if (phoneNumber != null) {
            this.phoneNumber = phoneNumber;
        }
    }
}