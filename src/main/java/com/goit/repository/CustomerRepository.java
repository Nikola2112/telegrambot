package com.goit.repository;


import com.goit.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByChatId(Long chatId);
    List<Customer> findUsersByPhoneNumberIsNotNull();
}

