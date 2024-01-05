package com.goit.dto;

import com.goit.entity.Customer;
import com.goit.entity.CustomerRole;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredCustomerDto {

    private Long chatId;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^\\+?3?8?(0\\d{9})$")
    private String phoneNumber;
    private CustomerRole role;
    private Timestamp registeredAt;

    public Customer toCustomer() {
        return Customer.builder()
                .chatId(chatId)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .role(role)
                .registeredAt(registeredAt)
                .build();
    }
}
