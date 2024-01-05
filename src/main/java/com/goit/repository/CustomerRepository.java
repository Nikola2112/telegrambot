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
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.chatId = :chatId")
    boolean existsByChatId(@Param("chatId") Long chatId);
    List<Customer> findByRole(CustomerRole role);
}

