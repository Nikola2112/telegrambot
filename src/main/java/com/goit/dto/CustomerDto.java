package com.goit.dto;

import com.goit.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long chatId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Timestamp registeredAt;

    public CustomerDto(Long chatId, String firstName, String lastName, String phoneNumber, java.sql.Timestamp timestamp) {
    }


    public Customer toCustomer() {
        return Customer.builder()
                .chatId(chatId)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .registeredAt(registeredAt)
                .build();
    }
}