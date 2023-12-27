package com.goit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UpdatePhoneCustomerDto {
    private Long chatId;
    private String phoneNumber;


}