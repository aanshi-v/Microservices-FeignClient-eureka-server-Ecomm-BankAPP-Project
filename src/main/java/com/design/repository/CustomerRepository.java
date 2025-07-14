package com.design.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.design.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByAccountNumber(Long acc);

	boolean existsByAccountNumber(Long account); 
}
