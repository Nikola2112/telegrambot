package com.goit.dto;

import com.goit.entity.Customer;

import com.goit.repository.CustomerRepository;
import com.goit.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomerData {

        private final InvoiceService invoiceService;
        private final CustomerRepository customerRepository;


        @Transactional
        @Scheduled(cron = "0 */1 * ? * *")
        public void run() {
            System.out.println("Last run: " + LocalDateTime.now());
            List<Customer> usersByPhoneNumberIsNotNull = customerRepository.findUsersByPhoneNumberIsNotNull();
            usersByPhoneNumberIsNotNull.forEach(System.out::println);
        }
    }

