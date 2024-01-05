package com.goit.service;

import com.goit.dto.RegisteredCustomerDto;
import com.goit.entity.Customer;
import com.goit.entity.CustomerRole;
import com.goit.excetpion.ResourcesNotFound;
import com.goit.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findById(Long chatId) {
        return customerRepository.findById(chatId)
                .orElseThrow(() -> new ResourcesNotFound(chatId));
    }

    public void registered(RegisteredCustomerDto customerDto) {
        customerRepository.save(customerDto.toCustomer());
        log.info("Registered user with ID: " + customerDto.getChatId());
    }

    public boolean existsByChatId(Long chatId) {
        return customerRepository.existsByChatId(chatId);
    }

    public List<Customer> findByRole(CustomerRole customerRole) {
        return customerRepository.findByRole(customerRole);
    }

}
