package com.design.service;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.design.dto.FundTransferRequest;
import com.design.dto.RegistrationRequest;
import com.design.dto.StatementRequest;
import com.design.entity.Customer;
import com.design.entity.Transaction;
import com.design.repository.CustomerRepository;
import com.design.repository.TransactionRepository;


@Service
public class BankService {
	
	 @Autowired
	    private CustomerRepository customerRepo;

	    @Autowired
	    private TransactionRepository transactionRepo;

	    @Autowired
	    private ModelMapper mapper;

	    public Customer register(RegistrationRequest request) {
	        Customer customer = mapper.map(request, Customer.class);
	        customer.setAccountNumber((long) (Math.random() * 1_000_000_0000L));
	        customer.setBalance(10000);
	        return customerRepo.save(customer);
	    }

	    
//	    public String transfer(FundTransferRequest request) {
	    @Transactional 
	    public Transaction transfer(FundTransferRequest request) {
	        Customer from = customerRepo.findByAccountNumber(request.getFromAccount());
	        Customer to = customerRepo.findByAccountNumber(request.getToAccount());
	        
	        if (from == null) {
	            throw new RuntimeException("Account number " + request.getFromAccount() + " does not exist: ");
	        }
	        if (to == null) {
	            throw new RuntimeException("Account number " + request.getToAccount() + " does not exist: ");
	        }

	        if (from.getBalance() < request.getAmount()) {
	            throw new RuntimeException("Insufficient balance");
	        }

	        from.setBalance(from.getBalance() - request.getAmount());
	        to.setBalance(to.getBalance() + request.getAmount());
 
	        customerRepo.save(from);
	        customerRepo.save(to);

	        Transaction t = new Transaction();
	        t.setFromAccount(from.getAccountNumber());
	        t.setToAccount(to.getAccountNumber());
	        t.setAmount(request.getAmount());
	        t.setType("TRANSFER");
	        t.setTimestamp(LocalDateTime.now());
	        transactionRepo.save(t);

//	        return "Transfer successful";
	        return  transactionRepo.save(t);
	    }

	    
//	    public List<Transaction> getStatement(StatementRequest request) {
//	        return transactionRepo.findByFromAccountOrToAccount(request.getAccount(), request.getAccount());
//	    }

	    public List<Transaction> getStatement(StatementRequest request) {
	        boolean exists = customerRepo.existsByAccountNumber(request.getAccount());
	        if (!exists) {
	            throw new RuntimeException("Account number does not exist: " + request.getAccount());
	        }
	        return transactionRepo.findByFromAccountOrToAccount(request.getAccount(), request.getAccount());
	    }
	    
	    
	    
	    
	    	    
	    public Customer validateCustomer(Long customerId, Long accountNumber) {
	        Customer customer = customerRepo.findByAccountNumber(accountNumber);

	        if (customer == null) {
	            throw new RuntimeException("Account number does not exist");
	        }

	        if (!customer.getId().equals(customerId)) {
	            throw new RuntimeException("Customer ID does not match the account number");
	        }

	        return customer;
	    }
	    
	    
	    
	    
}
