package com.goit.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @NotBlank(message = "Ім'я не повинно бути порожнім")
    @Column(name = "customer_firsName", length = 50)
    private String firstName;

    @NotBlank(message = "Прізвище не повинно бути порожнім")
    @Column(name = "customer_lasName", length = 50)
    private String lastName;


    @Pattern(regexp = "\\+380\\d{9}", message = "Номер телефону повинен бути в форматі +380XXXXXXXXX")
    @Column(name = "customer_phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "register_at")
    private Timestamp registeredAt;
    @Enumerated(EnumType.STRING)
    private CustomerRole role;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Ticket> tickets = new ArrayList<>();


    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addTicker(Ticket ticket) {
        if (ticket != null) {
            tickets.add(ticket);
        }
    }
}