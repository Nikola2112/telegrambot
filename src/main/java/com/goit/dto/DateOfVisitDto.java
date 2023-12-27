package com.goit.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DateOfVisitDto {

    private Long chatId;
    private LocalDateTime dareOfVisit;
}
