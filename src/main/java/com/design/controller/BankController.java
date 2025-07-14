package com.design.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.design.dto.FundTransferRequest;
import com.design.dto.RegistrationRequest;
import com.design.dto.StatementRequest;
import com.design.entity.Customer;
import com.design.entity.Transaction;
import com.design.service.BankService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bank")
public class BankController {
	
	@Autowired
    private BankService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        Customer saved = service.register(request);
        return ResponseEntity.ok(response("Success", "Registration done.", 10000, saved));
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@Valid @RequestBody FundTransferRequest request) {
//        String msg = service.transfer(request);
    	 Transaction data = service.transfer(request); 
        return ResponseEntity.ok(response("Success", "Amount transferred", 10001, data));
    }

    @PostMapping("/statement")
    public ResponseEntity<?> statement(@Valid @RequestBody StatementRequest request) {
        List<Transaction> txns = service.getStatement(request);
        return ResponseEntity.ok(response("Success", "Statement fetched", 10002, txns));
    }

    private Map<String, Object> response(String status, String message, int code, Object data) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", status);
        map.put("message", message);
        map.put("code", code);
        map.put("data", data);
        return map;
    }
    
    
    
      
    @GetMapping("/validate")
    public ResponseEntity<?> validateCustomer(
            @RequestParam Long customerId,
            @RequestParam Long accountNumber) {

        try {
            Customer customer = service.validateCustomer(customerId, accountNumber);

            return ResponseEntity.ok(Map.of(
                "status", "SUCCESS",
                "message", "Customer validated",
                "code", 10005,
                "data", customer
            ));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "status", "FAIL",
                "message", ex.getMessage(),
                "code", 404,
                "data", null
            ));
        }
    }

    
    
    
    
    

}
