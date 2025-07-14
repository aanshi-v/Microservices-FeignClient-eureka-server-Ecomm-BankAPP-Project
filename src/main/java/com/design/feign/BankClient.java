package com.design.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.design.dto.TransferRequest;

//@FeignClient(name = "bank-client", url = "http://localhost:8080")
@FeignClient(name = "BankApp-API")
public interface BankClient {
	
	 @PostMapping("/api/bank/transfer")
	    ResponseEntity<Map<String, Object>> transfer(@RequestBody TransferRequest request);
	 
	 
	 
	 @GetMapping("/api/bank/validate")
	    ResponseEntity<Map<String, Object>> validateCustomer(
	        @RequestParam("customerId") Long customerId,
	        @RequestParam("accountNumber") Long accountNumber
	    );

}


