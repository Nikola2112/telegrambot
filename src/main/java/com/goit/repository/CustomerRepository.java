package com.goit.repository;


import com.goit.entity.Customer;

import com.goit.entity.CustomerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    boolean existsByChatId(Long chatId);
    List<Customer> findByRole(CustomerRole role);
}

