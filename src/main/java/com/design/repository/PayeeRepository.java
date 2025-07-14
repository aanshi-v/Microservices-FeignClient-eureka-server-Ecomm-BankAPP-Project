package com.design.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.design.entity.Payee;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {

	List<Payee> findByCustomerAccountNumber(Long accountNumber);
}
