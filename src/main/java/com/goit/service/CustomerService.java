package com.goit.service;

import com.goit.dto.*;
import com.goit.entity.Customer;

import com.goit.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
     @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findById(Long chatId) {
        return customerRepository.findById(chatId).orElseThrow();
    }
    public void registered(CustomerDto customerDto) {
        customerRepository.save(customerDto.toCustomer());
    }

    public boolean existsByChatId(Long chatId){
        return customerRepository.existsByChatId(chatId);
    }


    @Transactional
    public void updatePhoneNumber(UpdatePhoneCustomerDto phoneCustomer) {
        Customer customerUpdate = findById(phoneCustomer.getChatId());
        customerUpdate.updatePhoneNumber(phoneCustomer.getPhoneNumber());
    }
}